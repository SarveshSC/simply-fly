package com.hexaware.simplyfly.exception;

public class InsufficientPassengersException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientPassengersException() {
		super("Add atleast one passenger.");
	}
}
