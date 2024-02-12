package com.hexaware.simplyfly.exception;

public class AirlineNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AirlineNotFoundException(String id) {
		super("Airline with id " + id + " not found.");
	}
}
