package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;

@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@Column(nullable = false)
	@Positive
	private Double amount;
	
	@Column(nullable = false)
	private LocalDateTime bookingDateTime;

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "flightTripId",nullable = false)
	private FlightTrip flightTripForBooking;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentBookings")
	private Set<Payments> payments = new HashSet<>();

	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "customerId",nullable = false)
	private Customer customer;

	@ManyToMany(mappedBy = "bookings", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Passengers> passengers = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus status = BookingStatus.Booked;

	public Bookings() {
		super();
	}

	public Bookings(Integer bookingId, Double amount, LocalDateTime bookingDateTime, FlightTrip flightTripForBooking,
			Set<Payments> payments, Customer customer, Set<Passengers> passengers, BookingStatus status) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
		this.flightTripForBooking = flightTripForBooking;
		this.payments = payments;
		this.customer = customer;
		this.passengers = passengers;
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

	public FlightTrip getFlightTripForBooking() {
		return flightTripForBooking;
	}

	public void setFlightTripForBooking(FlightTrip flightTripForBooking) {
		this.flightTripForBooking = flightTripForBooking;
	}

	public Set<Payments> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payments> payments) {
		this.payments = payments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passengers> passengers) {
		this.passengers = passengers;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}
	
	
}
