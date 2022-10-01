package com.ctamayo.banca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
	
	@GetMapping(value = "/productos")
	public String productos() {
		return "Aqui debería estar la lista de productos";
	}
	
}
