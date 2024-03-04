package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

public class FlightTripDTO {	
	@Future
	private LocalDateTime departure;
	
	@Future
	private LocalDateTime arrival;
	
	@Min(value = 1000)
	private Double ticketPrice;
	
	private String source;
	
	private String destination;
	
	private String status;
	
	private Integer flightTripId;
	
	private Integer filledSeats;
	
	public FlightTripDTO() {
		super();
	}

	public FlightTripDTO(@Future LocalDateTime departure, @Future LocalDateTime arrival, @Min(1000) Double ticketPrice,
			String source, String destination, String status, Integer flightTripId, Integer filledSeats) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.source = source;
		this.destination = destination;
		this.status = status;
		this.flightTripId = flightTripId;
		this.filledSeats = filledSeats;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFlightTripId() {
		return flightTripId;
	}

	public void setFlightTripId(Integer flightTripId) {
		this.flightTripId = flightTripId;
	}

	public Integer getFilledSeats() {
		return filledSeats;
	}

	public void setFilledSeats(Integer filledSeats) {
		this.filledSeats = filledSeats;
	}
	
	
	
    
}
