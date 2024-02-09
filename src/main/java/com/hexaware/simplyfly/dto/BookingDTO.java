package com.hexaware.simplyfly.dto;

import java.time.LocalDateTime;

public class BookingDTO {

	private Integer bookingId;
	private Double amount;
	private LocalDateTime bookingDateTime;
	private String status;
	public BookingDTO(Integer bookingId, Double amount, LocalDateTime bookingDateTime, String status) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.bookingDateTime = bookingDateTime;
		this.status = status;
	}
	public BookingDTO() {
		super();
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
