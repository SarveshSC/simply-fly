package com.hexaware.simplyfly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	// @ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="id not found")
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleExp(Exception e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	


}
