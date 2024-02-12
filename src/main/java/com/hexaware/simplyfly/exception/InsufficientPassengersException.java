package com.hexaware.simplyfly.exception;

public class InsufficientPassengersException extends Exception {
	public InsufficientPassengersException() {
		super("Add atleast one passenger.");
	}
}
