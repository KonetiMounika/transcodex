package com.transcodex.carparking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.transcodex.carparking.exception.CarNotFoundException;
import com.transcodex.carparking.model.Car;
import com.transcodex.carparking.model.ParkingSpot;
import com.transcodex.carparking.repository.CarRepository;
import com.transcodex.carparking.repository.ParkingSpotRepository;

@SpringBootTest
public class ParkingServiceImplTest {
	@InjectMocks
	private ParkingServiceImpl parkingService;
	@Mock
	private CarRepository carRepository;
	@Mock
	private ParkingSpotRepository parkingSpotRepository;
	
	private Car car;
	private ParkingSpot parkingSpot;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void getCarDetails_Success() {
		// given
		Long carId = 1L;
		car = new Car();
		
		Mockito.when(carRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(car));
		
		// when
		parkingService.getCarDetails(carId);
		
		//then
		Mockito.verify(carRepository, Mockito.times(1)).findById(carId);
	}
	
	@Test
	public void getCarDetails_Failure() {
		// given
		Long carId = 1L;
		car = new Car();
		
		Mockito.when(carRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		// when
		
		//then
		assertThrows(CarNotFoundException.class, () -> parkingService.getCarDetails(carId));
	}
	
	@Test
	public void parkCar_Success() {
		Long carId = 1L;
		int spotNumber = 1;
		car = new Car();
		parkingSpot = new ParkingSpot();
		
		Mockito.when(parkingSpotRepository.findBySpotNumber(spotNumber)).thenReturn(parkingSpot);
		Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));
		
		parkingService.parkCar(carId, spotNumber);
		
		Mockito.verify(parkingSpotRepository, Mockito.times(1)).findBySpotNumber(spotNumber);
		Mockito.verify(carRepository, Mockito.times(1)).findById(carId);
		
	}
	
	@Test
	public void parkCar_CarNotFoundException() {
		Long carId = 1L;
		int spotNumber = 1;
		car = new Car();
		parkingSpot = new ParkingSpot();
		
		Mockito.when(parkingSpotRepository.findBySpotNumber(spotNumber)).thenReturn(parkingSpot);
		//Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));
		
		assertThrows(CarNotFoundException.class, () -> parkingService.parkCar(carId, spotNumber));		
	}
	
	@Test
	public void findAvailableParkingSpot_success() {
		List<ParkingSpot> parkingSpots = new ArrayList<>();
			ParkingSpot parkingSpot = new ParkingSpot();
		parkingSpot.setId(1L);
		parkingSpot.setSpotNumber(1);
		parkingSpot.setOccupied(false);
		parkingSpots.add(parkingSpot);
		
		Mockito.when(parkingSpotRepository.findByOccupied()).thenReturn(parkingSpots);
		int spot = parkingService.findAvailableParkingSpot();
		
		assertEquals(1, spot);
	}
}
