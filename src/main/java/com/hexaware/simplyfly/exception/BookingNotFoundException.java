package com.hexaware.simplyfly.exception;

public class BookingNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public BookingNotFoundException(String id) {
		super("Booking with id " + id + " not found");
	}
}
