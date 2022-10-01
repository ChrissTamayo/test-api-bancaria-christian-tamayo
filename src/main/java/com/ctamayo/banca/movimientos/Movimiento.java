package com.ctamayo.banca.movimientos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;

import com.ctamayo.banca.cuenta.Cuenta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "detalles_movimiento")
public class Movimiento {
	
	protected Movimiento() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movimientoId;
	
	//@Past(message = "fecha no puede ser mayor al dia de hoy")
	private LocalDate fecha;
	private String tipoMovimiento;
	private Double valor;
	private Double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Cuenta cuenta;
	
	public Integer getMovimientoId() {
		return movimientoId;
	}
	public void setMovimientoId(Integer movimientoId) {
		this.movimientoId = movimientoId;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Movimiento(Integer movimientoId, LocalDate fecha, String tipoMovimiento, Double valor, Double saldo) {
		super();
		this.movimientoId = movimientoId;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}
	
	public Movimiento( LocalDate fecha, String tipoMovimiento, Double valor, Double saldo) {
		super();
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "Movimiento [movimientoId=" + movimientoId + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento
				+ ", valor=" + valor + ", saldo=" + saldo + "]";
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
}
