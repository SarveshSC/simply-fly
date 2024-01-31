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
	PaymentStatus status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bookingId")
	private Bookings payment_bookings;
	
	public Payments() {
		super();
	}
	
}
