package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class FlightTripDTO {
	@NotBlank
	private Integer flightTripId;
	
	@Future
	private LocalDateTime departure;
	
	@Future
	private LocalDateTime arrival;
	
	@Min(value = 1000)
	private Double ticketPrice;
	
	
	
	public FlightTripDTO(Integer flightTripId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice
			) {
		super();
		this.flightTripId = flightTripId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		
		
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
	
	
    
}
