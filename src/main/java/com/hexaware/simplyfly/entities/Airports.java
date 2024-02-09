package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
public class Airports {
	@NotNull
	String name;
	@Id
	@Pattern(regexp = "[A-Z]{3}")
	String iataCode; //International Airport Transport Authority Code
	@NotNull
	String location;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "source")
	private Set<FlightTrip> flightsSource = new HashSet<FlightTrip>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "destination")
	private Set<FlightTrip> flightsDestination = new HashSet<FlightTrip>();
	
	public Airports() {
		super();
	}

	public Airports(String name, String abbrevation, String location) {
		super();
		this.name = name;
		this.iataCode = abbrevation;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return iataCode;
	}

	public void setCode(String abbrevation) {
		this.iataCode = abbrevation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public Set<FlightTrip> getFlightsSource() {
		return flightsSource;
	}

	public void setFlightsSource(Set<FlightTrip> flightsSource) {
		this.flightsSource = flightsSource;
	}

	public Set<FlightTrip> getFlightsDestination() {
		return flightsDestination;
	}

	public void setFlightsDestination(Set<FlightTrip> flightsDestination) {
		this.flightsDestination = flightsDestination;
	}
	
	
	
	
}
