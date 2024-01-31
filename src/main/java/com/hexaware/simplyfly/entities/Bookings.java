package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Bookings {
	@Id
	Integer bookingId;
	Double amount;
	LocalDateTime bookingDateTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flightDetailId")
	private FlightDetails flightdetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	private Set<BookingPassengers> bookings = new HashSet<BookingPassengers>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "payment_bookings")
	private Set<Payments> payments_bookings = new HashSet<Payments>();

	BookingStatus status;

	public Bookings() {
		super();
	}

	public Bookings(Integer bookingId, Double amount, LocalDateTime bookingDateTime, FlightDetails flightdetails,
			BookingStatus status) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
		this.flightdetails = flightdetails;
		this.status = status;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	public FlightDetails getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(FlightDetails flightdetails) {
		this.flightdetails = flightdetails;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

}
