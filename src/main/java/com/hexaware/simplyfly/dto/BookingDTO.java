package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.hexaware.simplyfly.entities.BookingStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class BookingDTO {

	@PositiveOrZero
	private Integer bookingId;
	
	@Positive
	private Double amount;
	
	@NotNull
	private LocalDateTime bookingDateTime;
	private BookingStatus status;
	
	@NotNull
	private Set<PassengerDTO> passengers = new HashSet<>();
	
	@NotNull
	private String customerId;
	
	@NotNull
	private Integer flightTripId;
	

	@NotNull
	private String paymentId;

	
	public BookingDTO() {
		super();
	}

	
	public BookingDTO(@PositiveOrZero Integer bookingId, @Positive Double amount,
			@NotNull LocalDateTime bookingDateTime, BookingStatus status, @NotNull Set<PassengerDTO> passengers,
			@NotNull Integer customerId, @NotNull Integer flightTripId, @NotNull String paymentId) {

		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
		this.status = status;
		this.passengers = passengers;
		this.customerId = customerId.toString();
		this.flightTripId = flightTripId;
		this.paymentId = paymentId;
	}
	public BookingDTO(@PositiveOrZero Integer bookingId, @Positive Double amount,
		    @NotNull LocalDateTime bookingDateTime, BookingStatus status,
		    @NotNull Set<PassengerDTO> passengers, @NotNull String customerId,
		    @NotNull Integer flightTripId) {
		    super();
		    this.bookingId = bookingId;
		    this.amount = amount;
		    this.bookingDateTime = bookingDateTime;
		    this.status = status;
		    this.passengers = passengers;
		    this.customerId = customerId;
		    this.flightTripId = flightTripId;
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
	public BookingStatus getStatus() {
		return status;
	}
	public void setStatus(BookingStatus status) {
		this.status = status;
	}
	public Set<PassengerDTO> getPassengers() {
		return passengers;
	}
	public void setPassengers(Set<PassengerDTO> passengers) {
		this.passengers = passengers;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getFlightTripId() {
		return flightTripId;
	}
	public void setFlightTripId(Integer flightTripId) {
		this.flightTripId = flightTripId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
}
