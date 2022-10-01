package com.ctamayo.banca.excepcion;

import java.time.LocalDateTime;

public class DetallesError {
	
	private LocalDateTime timestamp;
	private String mensage;
	private String descripcion;
	
	public DetallesError(LocalDateTime timestamp, String mensage, String descripcion) {
		super();
		this.timestamp = timestamp;
		this.mensage = mensage;
		this.descripcion = descripcion;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMensage() {
		return mensage;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
}
