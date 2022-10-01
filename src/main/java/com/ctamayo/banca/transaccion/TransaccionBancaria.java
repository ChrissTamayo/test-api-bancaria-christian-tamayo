package com.ctamayo.banca.transaccion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "detalles_transaccion")
public class TransaccionBancaria {
	
	public TransaccionBancaria() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identificacionTransaccion;
	private int numIdentificacionCliente;
	private int numIdentificacionCuenta;
	private int numIdentificacionMovimiento;
	private double limiteDiarioRetiro;
	private boolean estadoCuenta;
	private String tipoCuenta;
	private String monto;
	private String cuentaOrigen;
	private String cuentaDestino;
	private String tipoMovimiento;
	private String saldoDisponible;
	
	
	public TransaccionBancaria(int identificacionTransaccion, String numIdentificacioncliente, String monto, String cuentaOrigen, String cuentaDestino) {
		super();
		this.identificacionTransaccion = identificacionTransaccion;
		this.numIdentificacionCliente = numIdentificacionCliente;
		this.monto = monto;
		this.cuentaOrigen = cuentaDestino;
	}
	
	
	public int getNumIdentificacionCliente() {
		return numIdentificacionCliente;
	}
	public void setNumIdentificacionCliente(int numIdentificacionCliente) {
		this.numIdentificacionCliente = numIdentificacionCliente;
	}
	
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	
	public String getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}


	public String getTipoMovimiento() {
		return tipoMovimiento;
	}


	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}


	public String getSaldoDisponible() {
		return saldoDisponible;
	}


	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}


	public TransaccionBancaria(int identificacionTransaccion, int numIdentificacionCliente, String monto, String cuentaOrigen, String cuentaDestino,
			String tipoMovimiento, String saldoDisponible) {
		super();
		this.identificacionTransaccion = identificacionTransaccion;
		this.numIdentificacionCliente = numIdentificacionCliente;
		this.monto = monto;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.tipoMovimiento = tipoMovimiento;
		this.saldoDisponible = saldoDisponible;
	}


	public int getIdentificacionTransaccion() {
		return identificacionTransaccion;
	}


	public void setIdentificacionTransaccion(int identificacionTransaccion) {
		this.identificacionTransaccion = identificacionTransaccion;
	}


	public int getNumIdentificacionCuenta() {
		return numIdentificacionCuenta;
	}


	public void setNumIdentificacionCuenta(int numIdentificacionCuenta) {
		this.numIdentificacionCuenta = numIdentificacionCuenta;
	}


	public boolean getEstadoCuenta() {
		return estadoCuenta;
	}


	public void setEstadoCuenta(boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}


	public String getTipoCuenta() {
		return tipoCuenta;
	}


	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}


	public int getNumIdentificacionMovimiento() {
		return numIdentificacionMovimiento;
	}


	public void setNumIdentificacionMovimiento(int numIdentificacionMovimiento) {
		this.numIdentificacionMovimiento = numIdentificacionMovimiento;
	}


	public double getLimiteDiarioRetiro() {
		return limiteDiarioRetiro;
	}


	public void setLimiteDiarioRetiro(double limiteDiarioRetiro) {
		this.limiteDiarioRetiro = limiteDiarioRetiro;
	}

	
}
