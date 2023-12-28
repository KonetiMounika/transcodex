package com.transcodex.carparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transcodex.carparking.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
