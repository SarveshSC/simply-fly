package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
import jakarta.validation.constraints.Positive;

@Entity
public class FlightTrip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightTripId;
	
	@Column(nullable = false)
	@Future
	private LocalDateTime departure;
	
	@Column(nullable = false)
	@Future
	private LocalDateTime arrival;
	
	@Column(nullable = false)
	@Min(value = 1000)
	private Double ticketPrice;
	
	
	@NotNull
	@Min(value = 0)
	@Positive
	private Integer filledSeats = 0;

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "flightCode")
	private Flights flights;

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "sourceIATACode", nullable = false)
	private Airports source;

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "destinationIATACode", nullable = false)
	private Airports destination;

	@OneToMany(mappedBy = "flightTripForBooking")
	private Set<Bookings> bookings = new HashSet<>();

	@OneToMany(mappedBy = "seatNo")
	private Set<SeatStructure> seats = new HashSet<>();

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "passengerFlightTripId")
//	private Set<Passengers> passengers = new HashSet<>();

	public FlightTrip() {
		super();
	}

	public FlightTrip(Integer flightTripId, @NotNull @Future LocalDateTime departure,
			@NotNull @Future LocalDateTime arrival, @NotNull @Min(1000) Double ticketPrice,
			@NotNull @Min(0) Integer filledSeats, Flights flights, Airports source, Airports destination,
			Set<Bookings> bookings, Set<SeatStructure> seats) {
		super();
		this.flightTripId = flightTripId;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketPrice = ticketPrice;
		this.filledSeats = filledSeats;
		this.flights = flights;
		this.source = source;
		this.destination = destination;
		this.bookings = bookings;
		this.seats = seats;
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

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
		this.flights = flights;
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

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}

	public Set<SeatStructure> getSeats() {
		return seats;
	}

	public void setSeats(Set<SeatStructure> seats) {
		this.seats = seats;
	}

}
