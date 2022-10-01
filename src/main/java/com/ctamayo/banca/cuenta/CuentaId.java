package com.ctamayo.banca.cuenta;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Embeddable
public class CuentaId implements Serializable{
	
	private Integer cuentaid;
	private Boolean estadoid;
	private String tipoCuenta;
	
	public Integer getCuentaid() {
		return cuentaid;
	}
	public void setCuentaid(Integer cuentaid) {
		this.cuentaid = cuentaid;
	}
	public Boolean getEstadoid() {
		return estadoid;
	}
	public void setEstadoid(Boolean estadoid) {
		this.estadoid = estadoid;
	}
	public CuentaId(Integer cuentaid, Boolean estadoid, String tipoCuenta) {
		super();
		this.cuentaid = cuentaid;
		this.estadoid = estadoid;
		this.tipoCuenta = tipoCuenta;
	}
	
	public CuentaId() {
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
}
