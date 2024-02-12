package com.hexaware.simplyfly.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class FlightDTO {
	
	@Pattern(regexp = "^[A-Z0-9]{2,3}-\\d{3,5}$")
	private String flightCode;
	
	@NotNull(message = "Total seats must not be null")
    @Positive(message = "Total seats must be a positive number")
	@Min(value=100)
	private Integer totalSeats;
	
	@NotNull(message = "Check-in weight must not be null")
	@Max(value=20)
	private Integer checkInWeight;
	
	@Max(value=7)
	@Positive(message = "Cabin weight must be a positive number")
	private Integer cabinWeight;

	private String airlineId;

	public FlightDTO() {
		super();
	}

	public FlightDTO(String flightCode, Integer totalSeats, Integer checkInWeight, Integer cabinWeight,
			String airlineId) {
		super();
		this.flightCode = flightCode;
		this.totalSeats = totalSeats;
		this.checkInWeight = checkInWeight;
		this.cabinWeight = cabinWeight;
		this.airlineId = airlineId;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getCheckInWeight() {
		return checkInWeight;
	}

	public void setCheckInWeight(Integer checkInWeight) {
		this.checkInWeight = checkInWeight;
	}

	public Integer getCabinWeight() {
		return cabinWeight;
	}

	public void setCabinWeight(Integer cabinWeight) {
		this.cabinWeight = cabinWeight;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	
}
