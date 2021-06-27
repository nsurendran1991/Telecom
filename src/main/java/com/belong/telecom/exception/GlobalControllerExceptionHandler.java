package com.belong.telecom.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {


	 @ExceptionHandler(PhoneNumberNotFoundException.class)
	    public ResponseEntity<Object> handleExceptions( PhoneNumberNotFoundException exception, WebRequest webRequest) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setDateTime(LocalDateTime.now());
	        response.setMessage(exception.getMessage());
	        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	        return entity;
	    }
	 
	 @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<Object> handleExceptions( CustomerNotFoundException exception, WebRequest webRequest) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setDateTime(LocalDateTime.now());
	        response.setMessage(exception.getMessage());
	        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	        return entity;
	    }
}
