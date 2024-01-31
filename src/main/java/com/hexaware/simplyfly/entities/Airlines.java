package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Airlines {
	@Id
	String airlineId;
	String airlineName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private  Set<Flights>  Flights = new HashSet<Flights>();  
	
	public Airlines() {
		super();
	}

	public Airlines(String airlineId, String airlineName) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public Set<Flights> getFlights() {
		return Flights;
	}

	public void setFlights(Set<Flights> flights) {
		Flights = flights;
	}
	
	
	
}
