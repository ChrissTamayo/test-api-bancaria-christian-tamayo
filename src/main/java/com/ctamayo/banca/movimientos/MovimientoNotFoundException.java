package com.ctamayo.banca.movimientos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MovimientoNotFoundException extends RuntimeException{
	
	public MovimientoNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
