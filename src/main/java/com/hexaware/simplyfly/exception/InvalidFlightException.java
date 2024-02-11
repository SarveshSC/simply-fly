package com.hexaware.simplyfly.exception;

public class InvalidFlightException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFlightException(String id) {
		super("Flight Trip with id " + id + " not found.");
	}
}
