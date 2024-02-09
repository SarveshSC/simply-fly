package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
	@Pattern(regexp = "[A-Z]{3}-[0-9]{3,}")
	private String flightCode;
	
	
	private LocalDateTime lastArrivalTime;
	private String LastArrivedAirportId;
	
	@NotNull(message = "Total seats must not be null")
    @Positive(message = "Total seats must be a positive number")
	@Min(value=100)
	private Integer totalSeats;
	
	@NotNull(message = "Check-in weight must not be null")
	@Max(value=20)
	private Integer checkInWeight;
	
	@Max(value=7)
	private Integer cabinWeight;
	
	//cascade = CascadeType.ALL//removed this in below
	@ManyToOne()
	@JsonIgnore//HELP WHILE RETRIEVING DATA IN SWAGGER WITHOUT ERROR
	@JoinColumn(name="airlineId")
	private Airlines airline;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "flights")
	private  Set<FlightTrip>  FlightTrip = new HashSet<FlightTrip>(); 
	
	
	

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
		// TODO Auto-generated constructor stub
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
		return FlightTrip;
	}

	public void setFlightTrip(Set<FlightTrip> flightTrip) {
		FlightTrip = flightTrip;
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
		return LastArrivedAirportId;
	}

	public void setLastArrivedAirportId(String lastArrivedAirportId) {
		LastArrivedAirportId = lastArrivedAirportId;
	}


	
	
}
