package com.hexaware.simplyfly.service;

import java.lang.System.Logger;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightsDto;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.AirlineRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightDetailsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class FlightServiceImpl implements IFlightService {
	
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	AirlineRepository airLineRepo;
	
	
	@Autowired
	AirportRepository airPortRepo;
	
	@Autowired
	FlightDetailsRepository flightDetailsRepo;

	@Override
	public Flights addFlights(FlightsDto flightDto,String sourceIata,String destinationIata,String airlineId) {
		Flights flight=new Flights();
		flight.setFlightCode(flightDto.getFlightCode());
		flight.setTotalSeats(flightDto.getTotalSeats());
		flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
		flight.setSource(airPortRepo.findById(sourceIata).orElse(null));
		flight.setDestination(airPortRepo.findById(destinationIata).orElse(null));
		return flightRepo.save(flight);	
	}

	@Override
	public Flights updateFlights(FlightsDto flightDto,String sourceIata,String destinationIata,String airlineId) {
		Flights flight=new Flights();
		flight.setFlightCode(flightDto.getFlightCode());
		flight.setTotalSeats(flightDto.getTotalSeats());
		flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
		flight.setSource(airPortRepo.findById(sourceIata).orElse(null));
		flight.setDestination(airPortRepo.findById(destinationIata).orElse(null));
		Flights flight1= flightRepo.save(flight);
		return flight1;
	}

	@Override
	public String removeFlights(String flightId) {
		if(flightRepo.existsById(flightId)) {
			flightRepo.deleteById(flightId);
			return "record deleted";
		}
		return "record not found";
	}

	@Override
	public List<Flights> flightsBySource(String sourceIata) {
		return flightRepo.findBySource(airPortRepo.findById(sourceIata).orElse(null));
	}

	@Override
	public List<Flights> flightsByDestination(String destinationIata) {
		return flightRepo.findByDestination(airPortRepo.findById(destinationIata).orElse(null));
	}

	
	
	





	

	
	
}
