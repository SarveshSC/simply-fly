package com.hexaware.simplyfly.exception;

public class FlightNotFoundException extends Exception {
	public FlightNotFoundException(String message) {
		super("Flight with id "+ message + " not found");
	}


}
