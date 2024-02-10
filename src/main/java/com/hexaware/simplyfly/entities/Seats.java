package com.hexaware.simplyfly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(SeatEmbeddedId.class)
public class Seats {

	@Enumerated(EnumType.STRING)
	private SeatStatus status;

	@Id
	@ManyToOne
	@JoinColumn(name = "flightTripId")
	private FlightTrip flightTripId;

	@Id
	@ManyToOne
	@JoinColumn(name = "seatNo")
	private SeatStructure seatNo;

	public Seats() {
		super();
	}

	public Seats(SeatStatus status, FlightTrip flightTripId, SeatStructure seatNo) {
		super();
		this.status = status;
		this.flightTripId = flightTripId;
		this.seatNo = seatNo;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	public FlightTrip getFlightTripId() {
		return flightTripId;
	}

	public void setFlightTripId(FlightTrip flightTripId) {
		this.flightTripId = flightTripId;
	}

	public SeatStructure getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(SeatStructure seatNo) {
		this.seatNo = seatNo;
	}

}
