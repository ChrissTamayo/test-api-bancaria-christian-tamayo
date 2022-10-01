package com.ctamayo.banca.excepcion;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ctamayo.banca.cliente.UserNotFoundException;

//Anotacion para que se agregue atodos los controladores 
@ControllerAdvice
public class ManejadorEntidadRespuestaExcepcionCustomizada extends ResponseEntityExceptionHandler {
	
	//Se coloca esta anotacion para manejar en este metodo todas las excepciones que se produzcan para error interno de servidor
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<DetallesError> manejaTodasExcepciones(Exception ex, WebRequest request) throws Exception {
		DetallesError detallesError = new DetallesError(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<DetallesError>(detallesError, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//Excepcion personalizada para el caso del que no se encuentre un usuario 
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<DetallesError> manejaExcepcionUsuarioNoEncontrado(Exception ex, WebRequest request) throws Exception {
		DetallesError detallesError = new DetallesError(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<DetallesError>(detallesError, HttpStatus.NOT_FOUND);
		
	}
	
	//Excepcion personalizada para el caso de validacion de campos	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		DetallesError detallesError = new DetallesError(LocalDateTime.now(), 
				"Errores totales: " + ex.getErrorCount() + ". Primer error: " + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
		return new ResponseEntity(detallesError, HttpStatus.BAD_REQUEST);
	}
	
}
