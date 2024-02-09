package com.hexaware.simplyfly.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payments {
	@Id
	Integer paymentId;
	String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bookingId")
	private Bookings paymentBookings;
	
	public Payments() {
		super();
	}

	public Payments(Integer paymentId, String status, Bookings paymentBookings) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bookings getPaymentBookings() {
		return paymentBookings;
	}

	public void setPaymentBookings(Bookings paymentBookings) {
		this.paymentBookings = paymentBookings;
	}
	
	
	
}
