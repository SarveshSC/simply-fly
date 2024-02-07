package com.hexaware.simplyfly.dto;

public class AirportDTO {
	private String name;
	private String iataCode; //International Airport Transport Authority Code
	private String location;
	
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
