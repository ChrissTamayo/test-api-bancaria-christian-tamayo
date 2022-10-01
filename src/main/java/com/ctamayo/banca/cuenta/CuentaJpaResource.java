package com.ctamayo.banca.cuenta;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ctamayo.banca.cliente.Cliente;
import com.ctamayo.banca.cliente.UserNotFoundException;
import com.ctamayo.banca.jpa.CuentaRepository;
import com.ctamayo.banca.jpa.MovimientoRepository;
import com.ctamayo.banca.movimientos.Movimiento;

@RestController
@RequestMapping("/productos/v2")
public class CuentaJpaResource {

	private CuentaRepository repositorio;
	private MovimientoRepository repositorioMovimiento;
	
	public CuentaJpaResource(CuentaRepository repositorio, MovimientoRepository repositorioMovimiento) {
		this.repositorio = repositorio;
		this.repositorioMovimiento = repositorioMovimiento;
	}
		
	// Obtener cuentas
	@GetMapping("/cuentas")
	public List<Cuenta> recuperarTodosCuentas(){
		return repositorio.findAll();
	}
		
	// Obtener  cuenta
	@GetMapping("/cuentas/{id}/{estado}/{tipo}")
	public EntityModel<Cuenta> recuperarCuenta(@PathVariable Integer id, @PathVariable Boolean estado, @PathVariable String tipo){
		CuentaId pkCompuesta = new CuentaId(id, estado, tipo);
		Optional<Cuenta> cuenta = repositorio.findById(pkCompuesta);
		
		if(cuenta.isEmpty())
			throw new CuentaNotFoundException("id:"+id);
		
		EntityModel<Cuenta> modeloEntidad = EntityModel.of(cuenta.get());
			
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).recuperarTodosCuentas());
		modeloEntidad.add(link.withRel("todos-cuentas"));
		
		return modeloEntidad;
	}
		
	// Eliminar cuenta
	@DeleteMapping("/cuentas/{id}/{estado}/{tipo}")
	public void eliminarCuenta(@PathVariable Integer id, @PathVariable Boolean estado, @PathVariable String tipo){
		CuentaId pkCompuesta = new CuentaId(id, estado, tipo);	
		repositorio.deleteById(pkCompuesta);
		
	}
	
	// Obtener movimientos de una cuenta
	@GetMapping("cuentas/{id}/{estado}/{tipo}/reportes")
	public ArrayList obtenerMovimientosPorUsuarioFecha(@PathVariable Integer id, @PathVariable Boolean estado, 
													   @PathVariable String tipo, @RequestParam String fechaDesde, @RequestParam String fechaHasta){
		
		//Se recupera existencia de cuentas por usuario
		CuentaId pkCompuesta = new CuentaId(id, estado, tipo);
		Optional<Cuenta> cuenta = repositorio.findById(pkCompuesta);
		
		
		if(cuenta.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		//Parseo de valores fecha recibidos en QueryParms
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate localDateDesde = LocalDate.parse(fechaDesde, formatter);
		localDateDesde = localDateDesde.minusDays(1);
		LocalDate localDateHasta = LocalDate.parse(fechaHasta, formatter);
		localDateHasta = localDateHasta.plusDays(1);
		
		//Mapeo de valores de reporte en formato customizado
		HashMap<String, String> map = new HashMap<>();
		ArrayList list = new ArrayList();
		
		
		for(Movimiento movimiento :cuenta.get().getMovimientos()) {
			
			 if (movimiento.getFecha().isAfter(localDateDesde) && movimiento.getFecha().isBefore(localDateHasta)) {
				 
				 map.put("fecha", movimiento.getFecha().toString());
				 map.put("cliente", cuenta.get().getCliente().getNombre());
				 map.put("numeroCuenta", cuenta.get().getNumeroCuenta());
				 map.put("tipo", cuenta.get().getCuentaId().getTipoCuenta());
				 map.put("saldoInicial", cuenta.get().getSaldoInicial()+"");
				 map.put("estado", cuenta.get().getCuentaId().getEstadoid()+"");
				 map.put("movimiento", movimiento.getValor()+"");
				 map.put("saldoDisponible", movimiento.getSaldo()+"");
				 list.add(map);
				 map = new HashMap<>();
			 }
		}
			if (list.size() < 1)
				throw new UserNotFoundException("id:"+id);
			
	    return list;
	}
	
	// Crear cuenta
		@PostMapping("/cuentas")
		public EntityModel<Cuenta> crearCuenta(@Valid @RequestBody Cuenta cuenta) {
			Cuenta cuentaGuardada = repositorio.save(cuenta);
			return EntityModel.of(cuentaGuardada);
		}
	
		
}