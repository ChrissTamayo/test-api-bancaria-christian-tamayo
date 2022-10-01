package com.ctamayo.banca.cuenta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CuentaNotFoundException extends RuntimeException{

	public CuentaNotFoundException(String mensaje) {
		super(mensaje);
	}
	
}
