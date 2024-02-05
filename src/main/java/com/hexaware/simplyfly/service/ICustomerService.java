package com.hexaware.simplyfly.service;

import com.hexaware.simplyfly.dto.BookingsDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;

public interface ICustomerService {
	public Customer createAccount(Customer customer);
	public Customer editAccountInfo(Customer customer);
	public boolean deleteAccount(String username);
	
	public Bookings bookFlight(BookingsDTO bookings);
	public boolean cancelBooking(Integer bookingId);
}
