package com.ctamayo.banca.service;

import java.util.List;

import com.ctamayo.banca.cuenta.Cuenta;

public interface TransaccionService {
	
	List<Cuenta>listarCuentas();
	Cuenta cuentaPorId(Integer id);
	void actualizarSaldoCuenta(Cuenta cuenta);
}
