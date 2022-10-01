package com.ctamayo.banca.cliente;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

import com.ctamayo.banca.cuenta.Cuenta;
import com.ctamayo.banca.jpa.ClienteRepository;
import com.ctamayo.banca.jpa.CuentaRepository;
import com.ctamayo.banca.movimientos.Movimiento;



@RestController
@RequestMapping("/personas/v2")
public class ClienteJpaResource {

	private ClienteRepository repositorio;
	private CuentaRepository repositorioCuenta;
	
	public ClienteJpaResource(ClienteRepository repositorio, CuentaRepository repositorioCuenta) {
		this.repositorio = repositorio;
		this.repositorioCuenta = repositorioCuenta;
	}
	
	// Obtener clientes
	@GetMapping("/clientes")
	public List<Cliente> recuperarTodosClientes(){
		return repositorio.findAll();
	}
	
	// Obtener cliente 
	@GetMapping("/clientes/{id}")
	public EntityModel<Cliente> recuperarCliente(@PathVariable Integer id){
		Optional<Cliente> cliente = repositorio.findById(id);
		
		if(cliente.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<Cliente> modeloEntidad = EntityModel.of(cliente.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).recuperarTodosClientes());
		modeloEntidad.add(link.withRel("todos-clientes"));
		
		return modeloEntidad;
	}
	
	// Eliminar cliente 
		@DeleteMapping("/clientes/{id}")
		public void eliminarPersona(@PathVariable Integer id){
			repositorio.deleteById(id);
	
		}
		
		// Obtener movimientos de una cuenta
		@GetMapping("/clientes/{id}/reportes")
		public ArrayList obtenerMovimientosPorUsuarioFecha(@PathVariable Integer id, @RequestParam String fechaDesde, @RequestParam String fechaHasta){
			Optional<Cliente> cliente = repositorio.findById(id);
			List<Cuenta> cuentasCliente = cliente.get().getCuentas();
			
			
			if(cliente.isEmpty())
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
			
			for(Cuenta cuenta :cuentasCliente) {
				
				for(Movimiento movimiento :cuenta.getMovimientos()) {
					
					 if (movimiento.getFecha().isAfter(localDateDesde) && movimiento.getFecha().isBefore(localDateHasta)) {
						 
						 map.put("fecha", movimiento.getFecha().toString());
						 map.put("cliente", cuenta.getCliente().getNombre());
						 map.put("numeroCuenta", cuenta.getNumeroCuenta());
						 map.put("tipo", cuenta.getCuentaId().getTipoCuenta());
						 map.put("saldoInicial", cuenta.getSaldoInicial()+"");
						 map.put("estado", cuenta.getCuentaId().getEstadoid()+"");
						 map.put("movimiento", movimiento.getValor()+"");
						 map.put("saldoDisponible", movimiento.getSaldo()+"");
						 list.add(map);
						 map = new HashMap<>();
					 }
				}
					
					
			  
				
			}
			if (list.size() < 1)
				throw new UserNotFoundException("id:"+id);
			return list;
			
		}
		
	// Obtener cuentas de un cliente
		@GetMapping("/clientes/{id}/cuentas")
		public List<Cuenta> obtenerCuentasPorUsuario(@PathVariable Integer id){
			Optional<Cliente> cliente = repositorio.findById(id);
			
			if(cliente.isEmpty())
				throw new UserNotFoundException("id:"+id);
			
			return cliente.get().getCuentas();
		}
	
	// Crear cliente
	@PostMapping("/clientes")
	public ResponseEntity<Object> crearCliente(@Valid @RequestBody Cliente cliente) {
		Cliente clienteGuardado = repositorio.save(cliente);
		// asigna el valor de la url get del nuevo cliente creado en la propiedad location del response
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(clienteGuardado.getpersonaId())
						.toUri(); //
		//Se genera una clase response entity para que se mapee el estado creado 201
		return ResponseEntity.created(location).build();
	}
	
	// 	Modificar telefono Patch
	@PatchMapping("/clientes")
	public void actualizarCelularCliente(@RequestBody Cliente cliente){
		Optional<Cliente> buscarCliente = repositorio.findById(cliente.getpersonaId());
		
		if(buscarCliente.isPresent()) {
			Cliente clienteActualizado = buscarCliente.get();
			clienteActualizado.setTelefono(cliente.getTelefono());
			repositorio.save(clienteActualizado);
			
		}else 
		{
			throw new UserNotFoundException("id:"+ cliente.getpersonaId());
		}
		
	}
	
	// 	Actualziar cliente 
	@PutMapping("/clientes/{id}")
	public void modificarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
		Optional<Cliente> buscarCliente = repositorio.findById(cliente.getpersonaId());
		
		if(buscarCliente.isPresent()) {
			Cliente clienteModificado = buscarCliente.get();
			clienteModificado.setContrasena(cliente.getContrasena());
			clienteModificado.setDireccion(cliente.getDireccion());
			clienteModificado.setEdad(cliente.getEdad());
			clienteModificado.setEstado(cliente.getEstado());
			clienteModificado.setGenero(cliente.getGenero());
			clienteModificado.setIdentificacion(cliente.getIdentificacion());
			clienteModificado.setNombre(cliente.getNombre());			
			clienteModificado.setTelefono(cliente.getTelefono());
			repositorio.save(clienteModificado);
			
		}else 
		{
			throw new UserNotFoundException("id:"+ cliente.getpersonaId());
		}
		
	}
	
	// Crear cuenta de un cliente
	@PostMapping("/clientes/{id}/cuentas")
	public ResponseEntity<Object> crearCuentasPorUsuario(@PathVariable Integer id, @Valid @RequestBody Cuenta cuenta){
		Optional<Cliente> cliente = repositorio.findById(id);
		
		if(cliente.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		cuenta.setCliente(cliente.get());
		Cuenta cuentaGuardada = repositorioCuenta.save(cuenta);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cuentaGuardada.getCuentaId())
				.toUri(); //
		//Se genera una clase response entity para que se mapee el estado creado 201
		return ResponseEntity.created(location).build();
	}
}
