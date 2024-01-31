package com.hexaware.simplyfly.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookingPassengers {
	@Id
	private int bookingPassengerId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bookingId")
	private Bookings booking;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="passengerId")
	private Passengers passenger;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="seatNo")
	private Seats seat;

	protected BookingPassengers() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected BookingPassengers(int bookingPassengerId, Bookings booking, Passengers passenger, Seats seat) {
		super();
		this.bookingPassengerId = bookingPassengerId;
		this.booking = booking;
		this.passenger = passenger;
		this.seat = seat;
	}

	public int getBookingPassengerId() {
		return bookingPassengerId;
	}

	public void setBookingPassengerId(int bookingPassengerId) {
		this.bookingPassengerId = bookingPassengerId;
	}

	public Bookings getBooking() {
		return booking;
	}

	public void setBooking(Bookings booking) {
		this.booking = booking;
	}

	public Passengers getPassenger() {
		return passenger;
	}

	public void setPassenger(Passengers passenger) {
		this.passenger = passenger;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}
	
	
}
