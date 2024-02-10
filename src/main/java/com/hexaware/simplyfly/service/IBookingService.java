package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Set;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Passengers;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.CustomerNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InsufficientPassengersException;
import com.hexaware.simplyfly.exception.InvalidSeatException;
import com.hexaware.simplyfly.exception.SeatNotVacantException;

public interface IBookingService {
	public Bookings bookFlight(BookingDTO bookingDTO, String customerId) throws CustomerNotFoundException, SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException;
	public List<Bookings> getAllBookingsByUsername(String username);
	public String cancelBooking(Integer bookingId) throws BookingNotFoundException;
}
