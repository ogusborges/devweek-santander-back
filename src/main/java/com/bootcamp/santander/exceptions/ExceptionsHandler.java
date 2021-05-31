package com.bootcamp.santander.exceptions;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ExceptionResponse> handleSecurity(BusinessException exception) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ExceptionResponse(exception.getMessage()));
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<ExceptionResponse> handleSecurity(NotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ExceptionResponse(exception.getMessage()));
	}

	@ExceptionHandler(IncorrectFormatException.class)
	protected ResponseEntity<ExceptionResponse> handleSecurity(IncorrectFormatException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionResponse(exception.getMessage()));
	}
}
