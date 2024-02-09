package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

public class FlightTripDTO {
	private Integer flightDetailId;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private Double ticketPrice;
	private Integer filledSeats;
	
	public FlightTripDTO(Integer flightDetailId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice,
			Integer filledSeats) {
		super();
		this.flightDetailId = flightDetailId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.filledSeats = filledSeats;
		
	}
	public FlightTripDTO() {
		super();
	}
	public Integer getFlightDetailId() {
		return flightDetailId;
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
