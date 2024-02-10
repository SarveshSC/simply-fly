package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Pattern;

@Entity
public class Airlines {
	@Id
	@Pattern(regexp ="[A-Z]{2,3}")
	String airlineId;
	
	@Column(name = "airlineName", nullable = false)
	String airlineName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "airline")
	private  Set<Flights>  flights = new HashSet<>();  
	
	@OneToOne(mappedBy = "airlineFromUser")
	@JsonIgnore
	private User user;
	
	public Airlines() {
		super();
	}

	public Airlines(@Pattern(regexp = "[A-Z]{2,3}") String airlineId, String airlineName, Set<Flights> flights,
			User user) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
		this.flights = flights;
		this.user = user;
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
		return flights;
	}

	public void setFlights(Set<Flights> flights) {
		this.flights = flights;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
