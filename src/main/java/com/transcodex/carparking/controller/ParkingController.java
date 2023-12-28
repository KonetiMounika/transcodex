package com.transcodex.carparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transcodex.carparking.exception.CarNotFoundException;
import com.transcodex.carparking.exception.ParkingSpotNotFounddException;
import com.transcodex.carparking.exception.ParkingSpotOccupiedException;
import com.transcodex.carparking.exception.ValidationErrorException;
import com.transcodex.carparking.model.Car;
import com.transcodex.carparking.service.ParkingService;

@RestController
@RequestMapping("/api/cars")
public class ParkingController {
	
	@Autowired
	private ParkingService parkingService;
	
	@GetMapping("/{carId}")
	public ResponseEntity<Car> getCarDetails(@PathVariable Long carId){
		Car car = parkingService.getCarDetails(carId);
		return ResponseEntity.ok(car);
	}
	
	@PostMapping("/park/{carId}/{spotNumber}")
	public ResponseEntity<String> parkCar(@PathVariable Long carId, @PathVariable int spotNumber){
		try {
		parkingService.parkCar(carId, spotNumber);
		return ResponseEntity.ok("Car parked successfully!");
		} catch(CarNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch(ParkingSpotOccupiedException | ParkingSpotNotFounddException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(ValidationErrorException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/findAvailbleSpot")
	public ResponseEntity<Integer> findAvailableParkingSpot(){
		int availableParkingSpot = parkingService.findAvailableParkingSpot();
		return ResponseEntity.ok(availableParkingSpot);
	}

}
