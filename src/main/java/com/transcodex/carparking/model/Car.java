package com.transcodex.carparking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 10)
	private String color;
	
	@NotBlank
	@Size(max = 25)
	private String brand;
	
	@NotBlank
	@Size(max = 20)
	private String licensePlate;
	
	@OneToOne(mappedBy = "car")
	private ParkingSpot parkingSpot;

}
