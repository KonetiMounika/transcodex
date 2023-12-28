package com.transcodex.carparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParkingSpotOccupiedException extends RuntimeException{
	public ParkingSpotOccupiedException(String message) {
		super(message);
	}

}
