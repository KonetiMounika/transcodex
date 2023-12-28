package com.transcodex.carparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transcodex.carparking.exception.CarNotFoundException;
import com.transcodex.carparking.exception.ParkingSpotOccupiedException;
import com.transcodex.carparking.model.Car;
import com.transcodex.carparking.model.ParkingSpot;
import com.transcodex.carparking.repository.CarRepository;
import com.transcodex.carparking.repository.ParkingSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingServiceImpl implements ParkingService{
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	@Override
	public Car getCarDetails(Long carId) {
		return carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));
	}

	/* The parkCar method now includes logic for parking a car in a specific spot */
	
	@Transactional
	@Override
	public void parkCar(Long carId, int spotNumber) {
		synchronized (this) {
		ParkingSpot parkingSpot = parkingSpotRepository.findBySpotNumber(spotNumber);
		if(parkingSpot.isOccupied()) {
			throw new ParkingSpotOccupiedException("Parking Spot " + spotNumber + " is already occupied.");
		}
		
		Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car not found with id: " + carId));
		
		parkingSpot.setCar(car);
		parkingSpot.setOccupied(true);
		parkingSpotRepository.save(parkingSpot);
		}		
	}
	
	/* findAvailableParkingSpot method has added to find and reserve the first available parking spot for a car */
	@Transactional
	@Override
	public int findAvailableParkingSpot() {
		List<ParkingSpot> availableSpots = parkingSpotRepository.findByOccupied();
		if(availableSpots.isEmpty()) {
			throw new ParkingSpotOccupiedException("No availble parking spots right now");
		}
		
		ParkingSpot availableSpot = availableSpots.get(0);
		availableSpot.setOccupied(true);
		parkingSpotRepository.save(availableSpot);
		
		return availableSpot.getSpotNumber();
	}

}
