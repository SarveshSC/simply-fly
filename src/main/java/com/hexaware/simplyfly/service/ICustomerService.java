package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.exception.InvalidFlightException;

public interface ICustomerService {
	public Customer createAccount(Customer customer);
	public Customer editAccountInfo(Customer customer);
	public String deleteAccount(String username);
	
	public List<SeatStructure> getVacantSeats(Integer flightTripId) throws InvalidFlightException;
}
