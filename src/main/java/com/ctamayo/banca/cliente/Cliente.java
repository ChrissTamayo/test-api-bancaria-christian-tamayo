package com.ctamayo.banca.cliente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ctamayo.banca.cuenta.Cuenta;
import com.ctamayo.banca.persona.Persona;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "detalles_cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "personaId")
public class Cliente extends Persona {
	
	protected Cliente() {
	 super();	
	}
	
	@NotNull
	@Size(min = 6, message = "contraseña debe contener al menos seis caracteres")
	private String contrasena;
	private Boolean estado;
	
	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Cuenta> cuentas;
	
	public Cliente(String contrasena, Boolean estado,
			Integer personaId, String nombre, String genero, int edad, String identificacion, String direccion,
			String telefono) {
		super(personaId, nombre, genero, edad, identificacion, direccion, telefono);


		this.contrasena = contrasena;
		this.estado = estado;
	}

	


	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}	
	@Override
	public String toString() {
		return "Cliente [contrasena=" + contrasena + ", estado=" + estado
				+ "]";
	}
}
