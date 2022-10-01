package com.ctamayo.banca.persona;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "detalles_persona")
@Inheritance (strategy = InheritanceType.JOINED)
public class Persona {
	
	protected Persona() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("clienteId")
	private Integer personaId;
	@NotNull
	@Size(min = 2, message = "Nombre debe contener al menos dos caracteres")
	private String nombre;
	@NotNull
	@Size(min = 1, message = "genero debe contener al menos un caracter")
	private String genero;
	@NotNull (message = "edad no debe ser nula")
	private int edad;
	@NotNull
	@Size(min = 2, message = "identificacion debe contener al menos dos caracteres")
	private String identificacion;
	private String direccion;
	private String telefono;
	
	public Persona(Integer personaId, String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono) {
		super();
		this.personaId = personaId;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getpersonaId() {
		return personaId;
	}

	public void setpersonaId(Integer personaId) {
		this.personaId = personaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Persona [personaId=" + personaId + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad
				+ ", identificacion=" + identificacion + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
}
