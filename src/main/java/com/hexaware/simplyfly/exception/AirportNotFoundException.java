package com.hexaware.simplyfly.exception;

public class AirportNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public AirportNotFoundException(String id) {
		super("Airport with id " + id + " not found.");
	}
}
