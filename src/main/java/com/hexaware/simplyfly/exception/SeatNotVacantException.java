package com.hexaware.simplyfly.exception;

public class SeatNotVacantException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeatNotVacantException(String message) {
		super("The seat " + message + " is already booked");
	}
}
