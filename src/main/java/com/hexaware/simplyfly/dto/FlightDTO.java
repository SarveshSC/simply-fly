package com.hexaware.simplyfly.dto;

public class FlightDTO {
	private String flightCode;
	private Integer totalSeats;
	private Integer checkInWeight;
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
