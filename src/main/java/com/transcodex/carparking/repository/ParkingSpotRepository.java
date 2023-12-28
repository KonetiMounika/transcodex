package com.transcodex.carparking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.transcodex.carparking.model.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long>{
	ParkingSpot findBySpotNumber(int spotNumber);
	
	@Query("SELECT p from ParkingSpot p where p.occupied = false")
	List<ParkingSpot> findByOccupied();
}
