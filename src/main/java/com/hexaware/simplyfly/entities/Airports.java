package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Airports {
	String name;
	@Id
	String iataCode; //International Airport Transport Authority Code
	String location;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "source")
	private Set<Flights> flights_source = new HashSet<Flights>();
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "destination")
	private Set<Flights> flights_destination = new HashSet<Flights>();
	
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

	public String getAbbrevation() {
		return iataCode;
	}

	public void setAbbrevation(String abbrevation) {
		this.iataCode = abbrevation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
