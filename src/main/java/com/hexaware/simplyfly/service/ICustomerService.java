package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.UpdateProfileDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.exception.InvalidFlightException;

public interface ICustomerService {
	public Customer createAccount(Customer customer) throws Exception;
	public Customer editAccountInfo(Customer customer);
	public String deleteAccount(String username);
	
	public List<SeatStructure> getVacantSeats(Integer flightTripId) throws InvalidFlightException;
	
	
	public List<BookingDTO> getBookingsByCustomerUsername(String username);
	
	public String updateProfile(UpdateProfileDTO updateProfileDTO,String  username) throws Exception;
	
	public UpdateProfileDTO getProfille( String username);
}
