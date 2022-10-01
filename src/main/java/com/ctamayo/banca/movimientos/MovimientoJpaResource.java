package com.ctamayo.banca.movimientos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctamayo.banca.jpa.MovimientoRepository;

@RestController
@RequestMapping("/productos/v2")
public class MovimientoJpaResource {

	private MovimientoRepository repositorio;
	
	public MovimientoJpaResource(MovimientoRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	// Obtener movimientos
	@GetMapping("/movimientos")
	public List<Movimiento> recuperarTodosMovimientos(){
		return repositorio.findAll();
	}
			
	// Obtener  movimiento
	@GetMapping("/movimientos/{id}")
	public EntityModel<Movimiento> recuperarMovimentos(@PathVariable Integer id){
		Optional<Movimiento> movimiento = repositorio.findById(id);
			
		if(movimiento.isEmpty())
			throw new MovimientoNotFoundException("id:"+id);
		
		EntityModel<Movimiento> modeloEntidad = EntityModel.of(movimiento.get());
			
		WebMvcLinkBuilder link =  linkTo(methodOn(this.getClass()).recuperarTodosMovimientos());
		modeloEntidad.add(link.withRel("todos-movimientos"));
		return modeloEntidad;
		
	}
			
	// Eliminar movimiento
	@DeleteMapping("/movimientos/{id}")
	public void eliminarMovimiento(@PathVariable Integer id){
		repositorio.deleteById(id);
	}
}
