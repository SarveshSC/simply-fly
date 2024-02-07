package com.hexaware.simplyfly.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hexaware.simplyfly.dto.FlightDetailsDTO;
import com.hexaware.simplyfly.entities.FlightDetails;

public interface IFlightDetailsService {

	public FlightDetails scheduleFlight(FlightDetailsDTO flightDetailsDTO,String flightCode);
	public FlightDetails rescheduleFlightDetails(FlightDetails flightDetails);
	public String cancelFlights(int flightDetailId);
	public List<FlightDetails> getByDate(LocalDateTime departure);
	public  List<FlightDetails>  viewAllFlightDetails(String flightId);
	
}
