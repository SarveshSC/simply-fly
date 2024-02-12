package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AirportDTO {
	
	@NotBlank(message="Airport Name cannot be blank")
	@Pattern(regexp = "[A-Z]{3}")
	String name;
	
	@NotBlank(message="Airport Code cannot be blank")
	String iataCode; 
	
	@NotBlank(message="Airport location cannot be blank")
	String location;
	
	public AirportDTO() {
		super();
	}
	public AirportDTO(String name, String iataCode, String location) {
		super();
		this.name = name;
		this.iataCode = iataCode;
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
