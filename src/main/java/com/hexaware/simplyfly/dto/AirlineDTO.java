package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirlineDTO {
	
	@NotBlank(message = "Airline Id cannot be blank")
	@Size(max = 3)
	private String airlineId;
	
	@NotBlank(message = "Airline name cannot be blank")
	private String airlineName;

	public AirlineDTO(String airlineId, String airlineName) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
	}

	public AirlineDTO() {
		super();
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

}
