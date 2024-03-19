package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
public class Flights {
	@Id
	//@Pattern(regexp = "^[A-Z0-9]{2,3}-\\d{3,5}$")
	private String flightCode;
	
	private LocalDateTime lastArrivalTime;
	
	private String lastArrivedAirportId;
	
	@Column(nullable = false)
	@NotNull(message = "Total seats must not be null")
    @Positive(message = "Total seats must be a positive number")
	@Min(value=100)
	private Integer totalSeats = 180;
	
	@NotNull(message = "Check-in weight must not be null")
	@Column(nullable = false)
	@Max(value=20)
	private Integer checkInWeight;
	
	@Max(value=7)
	@Positive(message = "Cabin weight must be a positive number")
	private Integer cabinWeight;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="airlineId")
	private Airlines airline;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flights")
	private  Set<FlightTrip>  flightTrips = new HashSet<>(); 
	
	@Enumerated(EnumType.STRING)
	private FlightStaus flightStaus;

	public Flights(@Pattern(regexp = "[A-Z]{3}-[0-9]{3,}") String flightCode, @Min(100) Integer totalSeats,
			Integer checkInWeight, Integer cabinWeight, Airlines airline) {
		super();
		this.flightCode=flightCode;
		this.totalSeats = totalSeats;
		this.checkInWeight = checkInWeight;
		this.cabinWeight = cabinWeight;
		this.airline = airline;
		
	}

	public Flights() {
		super();
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public LocalDateTime getLastArrivalTime() {
		return lastArrivalTime;
	}

	public void setLastArrivalTime(LocalDateTime lastArrivalTime) {
		this.lastArrivalTime = lastArrivalTime;
	}

	public String getLastArrivedAirportId() {
		return lastArrivedAirportId;
	}

	public void setLastArrivedAirportId(String lastArrivedAirportId) {
		this.lastArrivedAirportId = lastArrivedAirportId;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getCheckInWeight() {
		return checkInWeight;
	}

	public void setCheckInWeight(Integer checkInWeight) {
		this.checkInWeight = checkInWeight;
	}

	public Integer getCabinWeight() {
		return cabinWeight;
	}

	public void setCabinWeight(Integer cabinWeight) {
		this.cabinWeight = cabinWeight;
	}

	public Airlines getAirline() {
		return airline;
	}

	public void setAirline(Airlines airline) {
		this.airline = airline;
	}

	public Set<FlightTrip> getFlightTrip() {
		return flightTrips;
	}

	public void setFlightTrip(Set<FlightTrip> flightTrip) {
		this.flightTrips = flightTrip;
	}

	public Set<FlightTrip> getFlightTrips() {
		return flightTrips;
	}

	public void setFlightTrips(Set<FlightTrip> flightTrips) {
		this.flightTrips = flightTrips;
	}

	public FlightStaus getFlightStaus() {
		return flightStaus;
	}

	public void setFlightStaus(FlightStaus flightStaus) {
		this.flightStaus = flightStaus;
	}

	
}
