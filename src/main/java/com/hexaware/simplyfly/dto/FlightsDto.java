package com.hexaware.simplyfly.dto;

public class FlightsDto {
	private String flightCode;
	private Integer totalSeats;
	
	public FlightsDto() {
		super();
	}
	public FlightsDto(String flightCode, Integer totalSeats) {
		super();
		this.flightCode = flightCode;
		this.totalSeats = totalSeats;
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
	
	
}
