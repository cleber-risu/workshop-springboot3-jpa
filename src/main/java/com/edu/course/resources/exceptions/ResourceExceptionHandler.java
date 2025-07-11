package com.edu.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edu.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

// Essa annotation faz com que intercptemos as exceções que acontecerem 
// para que este objeto posso executar um possivel tratamento
@ControllerAdvice
public class ResourceExceptionHandler {

	// essa annotation serve para que este metodo seja capaz de intercpetar a requisição que deu exceção
	// do tipo ResourceNotFoundException, que nos criamos anteriormente
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI()	);
		return ResponseEntity.status(status).body(err);
	}
}
