package com.transcodex.carparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationErrorException extends RuntimeException {
	public ValidationErrorException(String message) {
		super(message);
	}
}
