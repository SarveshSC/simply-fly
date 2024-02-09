package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
public class FlightTrip {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer flightDetailId;
	@NotNull
	@Future
	private LocalDateTime departure;
	@NotNull
	@Future
	private LocalDateTime arrival;
	@NotNull
	@Min(value=1000)
	private Double ticketPrice;
	@NotNull
	@Min(value=0)
	private Integer filledSeats;
	

	
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="flightCode")
	private Flights flights;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="sourceIATACode")
	private Airports source;
	
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="destinationIATACode")
	private Airports destination;
	

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightdetails")
	private Set<Bookings> bookings = new HashSet<Bookings>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flightTripId")
	private Set<Seats> seats = new HashSet<>();
	
	public FlightTrip() {
		super();
	}

	public FlightTrip(Integer flightDetailId, LocalDateTime departure, LocalDateTime arrival, Double ticketPrice,
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

	

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}

	public Airports getSource() {
		return source;
	}

	public void setSource(Airports source) {
		this.source = source;
	}

	public Airports getDestination() {
		return destination;
	}

	public void setDestination(Airports destination) {
		this.destination = destination;
	}
	
	
}
