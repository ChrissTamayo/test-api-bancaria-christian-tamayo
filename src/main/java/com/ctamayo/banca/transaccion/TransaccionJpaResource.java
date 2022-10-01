package com.ctamayo.banca.transaccion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctamayo.banca.cliente.UserNotFoundException;
import com.ctamayo.banca.cuenta.Cuenta;
import com.ctamayo.banca.cuenta.CuentaId;
import com.ctamayo.banca.jpa.CuentaRepository;
import com.ctamayo.banca.jpa.MovimientoRepository;
import com.ctamayo.banca.movimientos.Movimiento;

@RestController
@RequestMapping("/productos/v2")
public class TransaccionJpaResource {
	
	private CuentaRepository repositorioCuenta;
	private MovimientoRepository repositorioMovimiento;
	
	public TransaccionJpaResource(CuentaRepository repositorioCuenta, MovimientoRepository repositorioMovimiento) {
		this.repositorioCuenta = repositorioCuenta;
		this.repositorioMovimiento = repositorioMovimiento;
	}
	

//	 	Acreditar a la cuenta
		@PostMapping("/credito")
		public Map<String, String> acreditarCuenta(@RequestBody TransaccionBancaria transacccionBancaria){
			CuentaId pkCompuesta = new CuentaId(transacccionBancaria.getNumIdentificacionCuenta(), transacccionBancaria.getEstadoCuenta(), transacccionBancaria.getTipoCuenta());
			Optional<Cuenta> cuentaRecuperada = repositorioCuenta.findById(pkCompuesta);
			double saldo = cuentaRecuperada.get().getSaldoInicial();
			double monto =  Double.parseDouble(transacccionBancaria.getMonto());
			LocalDate lt = LocalDate.now();
			
			Movimiento mv = new Movimiento(transacccionBancaria.getNumIdentificacionMovimiento() ,lt, "Deposito", monto, saldo + monto);
			mv.setCuenta(cuentaRecuperada.get());
			repositorioMovimiento.save(mv);
			
			
			HashMap<String, String> map = new HashMap<>();
		    map.put("respuesta", "ok");
			
			return map;
		}
		
	//Debitar a la cuenta
		@PostMapping("/debito")
		public Map<String, String> debitarCuenta(@RequestBody TransaccionBancaria transacccionBancaria){
			CuentaId pkCompuesta = new CuentaId(transacccionBancaria.getNumIdentificacionCuenta(), transacccionBancaria.getEstadoCuenta(), transacccionBancaria.getTipoCuenta());
			Optional<Cuenta> cuentaRecuperada = repositorioCuenta.findById(pkCompuesta);
			double saldo = cuentaRecuperada.get().getSaldoInicial();
			double monto =  Double.parseDouble(transacccionBancaria.getMonto());
			
			if (saldo == 0) { 
					throw new UserNotFoundException("Saldo no disponible");}
				else if (saldo < monto) {
					throw new UserNotFoundException("fondos insuficientes");
				}
				
			
			LocalDate lt = LocalDate.now();
			
			Movimiento mv = new Movimiento(transacccionBancaria.getNumIdentificacionMovimiento() ,lt, "Retiro", monto, saldo - monto);
			mv.setCuenta(cuentaRecuperada.get());
			repositorioMovimiento.save(mv);
			
			
			HashMap<String, String> map = new HashMap<>();
		    map.put("respuesta", "ok");
			
			return map;
		}
}
