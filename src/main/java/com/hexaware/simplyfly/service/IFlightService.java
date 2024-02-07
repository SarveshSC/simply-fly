package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.dto.FlightsDto;
import com.hexaware.simplyfly.entities.Flights;


@Repository
public interface IFlightService  {

	public Flights addFlights(FlightsDto flightDto,String sourceIata,String destinationIata,String airlineId);
	public Flights updateFlights(FlightsDto flightDto,String sourceIata,String destinationIata,String airlineId);
	public String removeFlights(String flightId);
	public List<Flights> flightsBySource(String sourceIata);
	public List<Flights> flightsByDestination(String destinationIata);
}
