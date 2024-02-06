package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.BookingsDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;

public interface ICustomerService {
	public Customer createAccount(Customer customer);
	public Customer editAccountInfo(Customer customer);
	public String deleteAccount(String username);
	
	public Bookings bookFlight(BookingsDTO bookings);
	public List<Bookings> getAllBookings(String username);
	public String cancelBooking(Integer bookingId);
}
