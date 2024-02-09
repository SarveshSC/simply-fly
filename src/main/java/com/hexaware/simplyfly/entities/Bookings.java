package com.hexaware.simplyfly.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	private Double amount;
	private LocalDateTime bookingDateTime;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flightDetailId")
	private FlightTrip flightdetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentBookings")
	private Set<Payments> payments = new HashSet<Payments>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@ManyToMany(mappedBy = "bookings")
	private Set<Passengers> passengers = new HashSet<Passengers>();

	private String status;

	public Bookings() {
		super();
	}

	

	public Bookings(Integer bookingId, Double amount, LocalDateTime bookingDateTime, FlightTrip flightdetails,
			Set<Payments> payments, Customer customer, Set<Passengers> passengers, String status) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
		this.flightdetails = flightdetails;
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

	public FlightTrip getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(FlightTrip flightdetails) {
		this.flightdetails = flightdetails;
	}


	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Set<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<Passengers> passengers) {
		this.passengers = passengers;
	}

	public void addPassenger(Passengers passenger) {
		this.getPassengers().add(passenger);
	}
}
