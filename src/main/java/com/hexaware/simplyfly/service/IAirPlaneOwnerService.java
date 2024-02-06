package com.hexaware.simplyfly.service;

import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.entities.Flights;


@Repository
public interface IAirPlaneOwnerService  {

	public Flights addFlights(Flights flight, String airline);
	public Flights addFlightDetails(Flights flights,String flightCode,String source_iata,String destination_iata);
	public FlightDetails updateFlightDetails(FlightDetails flightDetails);
	public void deleteFlightDetails(int flightDetailsid);
	
}
