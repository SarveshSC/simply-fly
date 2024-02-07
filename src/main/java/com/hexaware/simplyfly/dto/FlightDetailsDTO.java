package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

public class FlightDetailsDTO {

	
	Integer flightDetailId;
	LocalDateTime departure;
	LocalDateTime arrival;
	Double ticketPrice;
	Integer filledSeats;
	Integer baggage;
	public FlightDetailsDTO(Integer flightDetailId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice,
			Integer filledSeats, Integer baggage) {
		super();
		this.flightDetailId = flightDetailId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.filledSeats = filledSeats;
		this.baggage = baggage;
	}
	public FlightDetailsDTO() {
		super();
	}
	public Integer getFlightDetailId() {
		return flightDetailId;
	}
	public void setFlightDetailId(Integer flightDetailId) {
		this.flightDetailId = flightDetailId;
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
	public Integer getBaggage() {
		return baggage;
	}
	public void setBaggage(Integer baggage) {
		this.baggage = baggage;
	}
	
	
	
}
