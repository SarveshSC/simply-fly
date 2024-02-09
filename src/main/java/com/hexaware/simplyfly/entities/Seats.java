package com.hexaware.simplyfly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Seats {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String status;
	
	@OneToOne(mappedBy = "seat")
	private Passengers passenger;
	
	@ManyToOne()
	@JoinColumn(name = "flightTripId")
	private FlightTrip flightTripId;
	
	@ManyToOne
    @JoinColumn(name = "seatNo")
    private SeatStructure seatStructure;
	
}
