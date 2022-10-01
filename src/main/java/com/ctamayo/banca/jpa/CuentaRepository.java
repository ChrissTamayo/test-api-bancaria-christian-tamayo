package com.ctamayo.banca.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctamayo.banca.cuenta.Cuenta;
import com.ctamayo.banca.cuenta.CuentaId;

public interface CuentaRepository  extends JpaRepository<Cuenta, CuentaId>{

}
