package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class FlightDetails {
	@Id
	Integer flightDetailId;
	LocalDateTime departure;
	LocalDateTime arrival;
	Double ticketPrice;
	Integer filledSeats;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="flightCode")
	Flights flights;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightdetails")
	private Set<Bookings> bookings = new HashSet<Bookings>();
	
	public FlightDetails() {
		super();
	}

	public FlightDetails(Integer flightDetailId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice,
			Integer filledSeats, Flights flights) {
		super();
		this.flightDetailId = flightDetailId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.filledSeats = filledSeats;
		this.flights = flights;
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

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
		this.flights = flights;
	}
	
	
}
