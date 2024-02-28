package com.hexaware.simplyfly.service;

import java.util.List;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.FlightScheduledExcpetion;
import com.hexaware.simplyfly.exception.UserNotFoundException;



public interface IFlightService  {

	public Flights addFlights(FlightDTO flightDto, String username) throws AirlineNotFoundException, UserNotFoundException, Exception;
	public Flights updateFlights(FlightDTO flightDto,String airlineId) throws AirlineNotFoundException, UserNotFoundException, Exception;
	public String removeFlights(String flightId, String username) throws AirlineNotFoundException, UserNotFoundException, FlightNotFoundException, FlightScheduledExcpetion;
	public List<FlightDTO> viewAllFlightsByUsername(String username) throws AirlineNotFoundException;
	
	public List<Flights> viewAllFlightsByAirlineId(String airlineId) throws AirlineNotFoundException;
	public List<Flights> viewAllFlights();
}
