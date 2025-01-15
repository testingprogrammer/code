package com.coding.challenge.prices.infraestructure.adapters.inbound.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.coding.challenge.prices.domain.exception.PriceNotFoundException;
 
@ControllerAdvice
public class PriceControllerAdvice {
	private static final String PRICE_NOT_FOUND_IN_DATABASE = "Price not found in database";
	private static final String ERROR = "Error";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
					HttpStatus.INTERNAL_SERVER_ERROR.value(),
					Instant.now(),
					ERROR,
					ex.getLocalizedMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException e) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				Instant.now(),
				ERROR,
				e.getLocalizedMessage());
		
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorMessage> missingServletRequestParameterExceptionExceptionHandler(MissingServletRequestParameterException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				Instant.now(),
				ERROR,
				ex.getLocalizedMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<ErrorMessage> priceNotFoundExceptionHandler(PriceNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(
				HttpStatus.NOT_FOUND.value(),
				Instant.now(),
				ERROR,
				PRICE_NOT_FOUND_IN_DATABASE);

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorMessage> bindExceptionHandler(BindException ex, WebRequest request) {

		 List<String> errors = ex.getBindingResult().getFieldErrors()
				 .stream()
				 .map(e ->String.format("%s %s", e.getField(),e.getDefaultMessage()))
				 .collect(Collectors.toList());
		 
		 ErrorMessage message = new ErrorMessage(
					HttpStatus.BAD_REQUEST.value(),
					Instant.now(),
					ERROR,
					errors.toString());
		
	      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
