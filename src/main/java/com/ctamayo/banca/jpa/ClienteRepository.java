package com.ctamayo.banca.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctamayo.banca.cliente.Cliente;
import com.ctamayo.banca.persona.Persona;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
