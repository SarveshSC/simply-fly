package com.hexaware.simplyfly.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.exception.InvalidTimingException;
import com.hexaware.simplyfly.exception.UserNotFoundException;

public interface IFlightTripService {

	public FlightTrip scheduleFlight(FlightTripDTO flightTripDTO, String flightCode, String sourceIata,
			String destinationIata,String username)
			throws FlightNotFoundException, AirportNotFoundException, InvalidTimingException, Exception;

	public FlightTrip rescheduleFlightTrip(FlightTripDTO flightTripdto,String username) throws Exception;

	public String cancelFlights(Integer flightDetailId,String username) throws BookingNotFoundException, InvalidFlightException, UserNotFoundException;

	public List<FlightTrip> getByDate(LocalDate departure);

	public List<FlightTripDTO> viewAllFlightTrip(String flightId) throws Exception;

	public List<FlightTrip> viewFlightBySourceAndDestination(String sourceIata, String destinationIata)
			throws AirportNotFoundException, Exception;

	public List<FlightTrip> getByDateAndSourceDestination(LocalDate departure, String sourceIata,
			String destinationIata) throws Exception;
	
	
	public List<FlightTripDTO> getAllFlightsByUsername(String username) throws UserNotFoundException;
	
	public List<BookingDTO> getAllBookingsByFlightTripId(int fightTripId,String username) throws  Exception;
	
	public List<BookingDTO> getAllBookingsByUsername(String username) throws UserNotFoundException;

}
