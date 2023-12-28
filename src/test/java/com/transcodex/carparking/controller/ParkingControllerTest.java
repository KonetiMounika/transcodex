package com.transcodex.carparking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockitoSession;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.transcodex.carparking.exception.CarNotFoundException;
import com.transcodex.carparking.exception.ParkingSpotNotFounddException;
import com.transcodex.carparking.exception.ValidationErrorException;
import com.transcodex.carparking.model.Car;
import com.transcodex.carparking.service.ParkingService;

@SpringBootTest
public class ParkingControllerTest {
	@InjectMocks
	private ParkingController parkingController;

	@Mock
	private ParkingService parkingService;
	
	private Car car;

	@Test
	public void parkCar_success() {
		Long carId = 1L;
		int spotNumber = 1;

		Mockito.doNothing().when(parkingService).parkCar(carId, spotNumber);

		ResponseEntity<String> parkCar = parkingController.parkCar(carId, spotNumber);

		assertEquals(HttpStatus.OK, parkCar.getStatusCode());
		assertEquals("Car parked successfully!", parkCar.getBody());

	}

	@Test
	public void parkCar_CarNotFound() {
		Long carId = 1L;
		int spotNumber = 1;

		Mockito.doThrow(CarNotFoundException.class).when(parkingService).parkCar(carId, spotNumber);
		ResponseEntity<String> parkCar = parkingController.parkCar(carId, spotNumber);

		assertEquals(HttpStatus.NOT_FOUND, parkCar.getStatusCode());
	}
	
	@Test
	public void parkCar_ParkingSpotNotFound() {
		Long carId = 1L;
		int spotNumber = 1;

		Mockito.doThrow(ParkingSpotNotFounddException.class).when(parkingService).parkCar(carId, spotNumber);
		ResponseEntity<String> parkCar = parkingController.parkCar(carId, spotNumber);

		assertEquals(HttpStatus.BAD_REQUEST, parkCar.getStatusCode());
	}
	
	@Test
	public void parkCar_ValidationError() {
		Long carId = 1L;
		int spotNumber = 1;

		Mockito.doThrow(ValidationErrorException.class).when(parkingService).parkCar(carId, spotNumber);
		ResponseEntity<String> parkCar = parkingController.parkCar(carId, spotNumber);

		assertEquals(HttpStatus.BAD_REQUEST, parkCar.getStatusCode());
	}
	
	@Test
	public void findAvailableParkingSpot_success(){
		Mockito.when(parkingService.findAvailableParkingSpot()).thenReturn(23);
		ResponseEntity<Integer> findAvailableParkingSpot = parkingController.findAvailableParkingSpot();
		assertEquals(HttpStatus.OK, findAvailableParkingSpot.getStatusCode());
	}
	
	@Test
	public void getCarDetails_success() {
		Long carId = 1L;
		car = new Car();
		
		Mockito.when(parkingService.getCarDetails(Mockito.anyLong())).thenReturn(car);
		ResponseEntity<Car> carDetails = parkingController.getCarDetails(carId);
		
		assertEquals(HttpStatus.OK, carDetails.getStatusCode());
		
	}
}
