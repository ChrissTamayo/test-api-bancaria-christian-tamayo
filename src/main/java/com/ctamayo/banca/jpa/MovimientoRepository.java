package com.ctamayo.banca.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctamayo.banca.movimientos.Movimiento;


public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{

}
