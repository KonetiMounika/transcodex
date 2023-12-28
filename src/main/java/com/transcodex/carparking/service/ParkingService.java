package com.transcodex.carparking.service;

import com.transcodex.carparking.model.Car;

public interface ParkingService {
	public Car getCarDetails(Long carId);
	public void parkCar(Long carId, int spotNumber);
	public int findAvailableParkingSpot();
}
