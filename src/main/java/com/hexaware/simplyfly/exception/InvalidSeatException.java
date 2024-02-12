package com.hexaware.simplyfly.exception;

public class InvalidSeatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSeatException() {
		super("Seat Number Invalid");
	}
}
