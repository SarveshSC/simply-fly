package com.hexaware.simplyfly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.AirlineRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightDetailsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class AirPlaneOwnerImpl implements IAirPlaneOwnerService {

	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	AirlineRepository airLineRepo;
	
	
	@Autowired
	AirportRepository airPortRepo;
	
	@Autowired
	FlightDetailsRepository flightDetailsRepo;
	
	
	@Override
	public Flights addFlights(Flights flight,String airline) {
		
		Airlines airline1=airLineRepo.findById(airline).orElse(null);
		if(airline1!=null)
		{
		flight.setAirline(airline1);
		
		}
		
	
		if(flightRepo.findById(flight.getFlightCode()) == null)
		{
			
			return flightRepo.save(flight);
		}
		return null;
		
		
	}


	@Override
	public Flights addFlightDetails(Flights flights, String flightCode, String source_iata,
			String destination_iata) {
	
		Flights flight1=flightRepo.findById(flightCode).orElse(null);
		if (flight1 != null ) 
		{
		
		
		Airports sourceAirport=airPortRepo.findById(source_iata).orElse(null);
		Airports destinationAirport=airPortRepo.findById(destination_iata).orElse(null);
		if(sourceAirport!=null && destinationAirport!=null)
		{
			flight1.setDestination(destinationAirport);
			flight1.setSource(sourceAirport);
//			flights.setFlights(flight1);
			return flight1;

			
		}
		
		
	}
		System.out.println("you have an error");
		return null;
		
			
	}


	@Override
	public FlightDetails updateFlightDetails(FlightDetails flightDetails) {
		 return flightDetailsRepo.save(flightDetails);
	}


	@Override
	public void deleteFlightDetails(int flightDetailsid) {
		
		flightDetailsRepo.deleteById(flightDetailsid);
		
	}


	
	
}
