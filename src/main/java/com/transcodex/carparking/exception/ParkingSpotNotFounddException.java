package com.transcodex.carparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParkingSpotNotFounddException extends RuntimeException{
	public ParkingSpotNotFounddException(String message) {
		super(message);
	}
}
