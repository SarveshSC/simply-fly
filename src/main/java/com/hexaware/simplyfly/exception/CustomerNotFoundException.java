package com.hexaware.simplyfly.exception;

public class CustomerNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String username) {
		super("Customer with username " + username + " not found.");
	}

}
