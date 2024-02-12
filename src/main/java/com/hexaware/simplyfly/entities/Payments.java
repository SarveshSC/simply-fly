package com.hexaware.simplyfly.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer paymentId;
	
	@Enumerated(EnumType.STRING)
	PaymentStatus status;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="bookingId")
	private Bookings paymentBookings;
	
	public Payments() {
		super();
	}

	public Payments(Integer paymentId, PaymentStatus status, Bookings paymentBookings) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.paymentBookings = paymentBookings;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Bookings getPaymentBookings() {
		return paymentBookings;
	}

	public void setPaymentBookings(Bookings paymentBookings) {
		this.paymentBookings = paymentBookings;
	}
	
	
	
}
