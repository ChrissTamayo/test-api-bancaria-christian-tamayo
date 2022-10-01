package com.ctamayo.banca.cuenta;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ctamayo.banca.cliente.Cliente;
import com.ctamayo.banca.movimientos.Movimiento;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name = "detalles_cuenta")
public class Cuenta {
	
	protected Cuenta() {
	}
	
	@EmbeddedId
	private CuentaId cuentaId;
	
	@NotNull
	@Size(min = 6, message = "cuenta debe contener al menos seis caracteres")
	private String numeroCuenta;

	@NotNull
	private Double saldoInicial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cliente cliente;
	
	@OneToMany(mappedBy = "cuenta")
	@JsonIgnore
	private List<Movimiento> movimientos;
	
	public Cuenta(String numeroCuenta, Double saldoInicial, 
		Boolean estado) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.saldoInicial = saldoInicial;
	}
	public Cuenta(CuentaId cuentaId, String numeroCuenta,
				Double saldoInicial) {
			super();
			this.numeroCuenta = numeroCuenta;
			this.saldoInicial = saldoInicial;
		}




	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}



	public Double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public List<Movimiento> getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	public CuentaId getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(CuentaId cuentaId) {
		this.cuentaId = cuentaId;
	}
	
	@Override
	public String toString() {
		return "Cuenta [ numeroCuenta=" + numeroCuenta
				+ ", saldoInicial=" + saldoInicial + "]";
	}
}
