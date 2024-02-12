package com.hexaware.simplyfly.exception;

public class SeatNotVacantException extends Exception{
	public SeatNotVacantException(String message) {
		super("The seat " + message + " is already booked");
	}
}
