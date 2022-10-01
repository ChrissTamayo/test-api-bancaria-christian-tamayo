package com.ctamayo.banca.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctamayo.banca.transaccion.TransaccionBancaria;

public interface Transaccion extends JpaRepository<TransaccionBancaria, Integer>{

}
