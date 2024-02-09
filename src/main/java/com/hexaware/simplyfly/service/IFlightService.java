package com.hexaware.simplyfly.service;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;



public interface IFlightService  {

	public Flights addFlights(FlightDTO flightDto,String airlineId) throws AirlineNotFoundException;
	public Flights updateFlights(FlightDTO flightDto,String airlineId) throws AirlineNotFoundException;
	public String removeFlights(String flightId) throws Exception;
	
}
