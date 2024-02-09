package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Set;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Passengers;
import com.hexaware.simplyfly.exception.BookingNotFoundException;

public interface IBookingService {
	public Bookings bookFlight(BookingDTO bookings, Set<Passengers> passengers);
	public List<Bookings> getAllBookings();
	public List<Bookings> getAllBookingsByUsername(String username);
	public String cancelBooking(Integer bookingId) throws BookingNotFoundException;
}
