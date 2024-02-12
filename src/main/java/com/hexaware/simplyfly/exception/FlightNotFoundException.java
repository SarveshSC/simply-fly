package com.hexaware.simplyfly.exception;

public class FlightNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException(String message) {
		super("Flight with id "+ message + " not found");
	}


}
