package com.hexaware.simplyfly.entities;

import java.io.Serializable;

public class SeatEmbeddedId implements Serializable {

	private static final long serialVersionUID = 1L;

	private FlightTrip flightTripId;

	private SeatStructure seatNo;

	public SeatEmbeddedId(FlightTrip flightTripId, SeatStructure seatStructureForSeats) {
		super();
		this.flightTripId = flightTripId;
		this.seatNo = seatStructureForSeats;
	}

	public SeatEmbeddedId() {
		super();
	}

	public FlightTrip getFlightTripId() {
		return flightTripId;
	}

	public void setFlightTripId(FlightTrip flightTripId) {
		this.flightTripId = flightTripId;
	}

	public SeatStructure getSeatStructureForSeats() {
		return seatNo;
	}

	public void setSeatStructureForSeats(SeatStructure seatStructureForSeats) {
		this.seatNo = seatStructureForSeats;
	}

}
