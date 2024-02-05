package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

public class BookingsDTO {
	private Integer bookingId;
	private Double amount;
	private LocalDateTime bookingDateTime;
	
	public BookingsDTO() {
		super();
	}

	public BookingsDTO(Integer bookingId, Double amount, LocalDateTime bookingDateTime) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
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
	
	
}
