package com.hexaware.simplyfly.entities;

import java.io.Serializable;

//@Embeddable
public class SeatEmbeddedId implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	@ManyToOne()
//	@JoinColumn(name = "flightTripId")
	private FlightTrip flightTripId;
	
//	@ManyToOne
//	@JoinColumn(name = "seatNo")
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
