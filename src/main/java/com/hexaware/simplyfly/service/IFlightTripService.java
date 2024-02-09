package com.hexaware.simplyfly.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InvalidTimingException;


public interface IFlightTripService {

	public FlightTrip scheduleFlight(FlightTripDTO flightTripDTO,String flightCode,String sourceIata,String destinationIata) throws FlightNotFoundException, AirportNotFoundException, InvalidTimingException, Exception;
	public FlightTrip rescheduleFlightTrip(FlightTrip flightTrip) throws Exception;
	public String cancelFlights(int flightDetailId);
	public List<FlightTrip> getByDate(LocalDate departure);
	public  List<FlightTrip>  viewAllFlightTrip(String flightId) throws Exception;
	public List<FlightTrip> viewFlightBySourceAndDestination(String sourceIata,String destinationIata) throws AirportNotFoundException, Exception;
	List<FlightTrip> getByDateAndSourceDestination(LocalDate departure, String sourceIata, String destinationIata)
			throws Exception;
	
}
