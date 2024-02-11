package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

public class FlightTripDTO {
	private Integer flightTripId;
	
	@Future
	private LocalDateTime departure;
	
	@Future
	private LocalDateTime arrival;
	
	@Min(value = 1000)
	private Double ticketPrice;
	
	private Integer filledSeats;
	
	public FlightTripDTO(Integer flightTripId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice,
			Integer filledSeats) {
		super();
		this.flightTripId = flightTripId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.filledSeats = filledSeats;
		
	}
	public FlightTripDTO() {
		super();
	}
	public Integer getFlightTripId() {
		return flightTripId;
	}
	public void setFlightTripId(Integer flightTripId) {
		this.flightTripId = flightTripId;
	}
	public LocalDateTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}
	public LocalDateTime getArrival() {
		return arrival;
	}
	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}
	public Double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Integer getFilledSeats() {
		return filledSeats;
	}
	public void setFilledSeats(Integer filledSeats) {
		this.filledSeats = filledSeats;
	}
    
}
