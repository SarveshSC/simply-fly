package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Flights {
	@Id
	private String flightCode;
	private Integer totalSeats;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="airlineId")
	private Airlines airline;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sourceIATACode")
	private Airports source;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="destinationIATACode")
	private Airports destination;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
	private  Set<Seats>  Seats = new HashSet<Seats>(); 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flights")
	private  Set<FlightDetails>  FlightDetails = new HashSet<FlightDetails>(); 
	
	public Flights() {
		super();
	}

	public Flights(String flightCode, Airlines airline, Airports source, Airports destination, Integer totalSeats) {
		super();
		this.flightCode = flightCode;
		this.airline = airline;
		this.source = source;
		this.destination = destination;
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

	public Airlines getAirline() {
		return airline;
	}

	public void setAirline(Airlines airline) {
		this.airline = airline;
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
