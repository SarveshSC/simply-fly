package com.hexaware.simplyfly.exception;

public class UserNotFoundException extends Exception{
	
	public UserNotFoundException(String message) {
		super("User with id " + message + " not found.");
	}
}
