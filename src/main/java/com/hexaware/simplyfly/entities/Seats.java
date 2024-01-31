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
public class Seats {
	@Id
	String seatNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="flightId")
	Flights flight;
//	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seat")
	private Set<BookingPassengers> seats = new HashSet<BookingPassengers>(); 
	
	public Seats() {
		super();
	}

	public Seats(String seatNo, Flights flight) {
		super();
		this.seatNo = seatNo;
		this.flight = flight;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}
	
	

}
