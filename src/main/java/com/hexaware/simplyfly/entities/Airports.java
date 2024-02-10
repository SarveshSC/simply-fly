package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Airports {

	@Id
	@Pattern(regexp = "[A-Z]{3}")
	String iataCode; // International Airport Transport Authority Code

	@Column(nullable = false)
	@Pattern(regexp = "[A-Z][a-z]")
	String name;

	@Column(nullable = false)
	@Pattern(regexp = "[A-Z][a-z]")
	String location;

	@OneToMany(mappedBy = "source")
	private Set<FlightTrip> flightsSource = new HashSet<>();

	@OneToMany(mappedBy = "destination")
	private Set<FlightTrip> flightsDestination = new HashSet<>();

	public Airports() {
		super();
	}

	public Airports(@NotNull String name, @Pattern(regexp = "[A-Z]{3}") String iataCode, @NotNull String location,
			Set<FlightTrip> flightsSource, Set<FlightTrip> flightsDestination) {
		super();
		this.name = name;
		this.iataCode = iataCode;
		this.location = location;
		this.flightsSource = flightsSource;
		this.flightsDestination = flightsDestination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
