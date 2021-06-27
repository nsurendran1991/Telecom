package com.belong.telecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such customer")
public class CustomerNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomerNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
