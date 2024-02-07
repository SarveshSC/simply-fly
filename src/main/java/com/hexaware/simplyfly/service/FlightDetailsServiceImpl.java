package com.hexaware.simplyfly.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.simplyfly.dto.FlightDetailsDTO;
import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightDetailsRepository;
import com.hexaware.simplyfly.repository.FlightRepository;

public class FlightDetailsServiceImpl implements IFlightDetailsService {

	@Autowired
	FlightDetailsRepository flightDetailsRepository;
	
	
	@Autowired
	FlightRepository flightRepository;
	
	
	@Override
	public FlightDetails scheduleFlight(FlightDetailsDTO flightDetailsDTO,String flightCode) {
		FlightDetails flightDetails=new FlightDetails();
		flightDetails.setFlightDetailId(flightDetailsDTO.getFlightDetailId());
		flightDetails.setArrival(flightDetailsDTO.getArrival());
		flightDetails.setDeparture(flightDetailsDTO.getDeparture());
		flightDetails.setFilledSeats(flightDetailsDTO.getFilledSeats());
		flightDetails.setTicketPrice(flightDetailsDTO.getTicketPrice());
		flightDetails.setFlights(flightRepository.findById(flightCode).orElse(null));
		return flightDetailsRepository.save(flightDetails);
		
	}


	@Override
	public FlightDetails rescheduleFlightDetails(FlightDetails flightDetails) {
		return flightDetailsRepository.save(flightDetails);
	}


	@Override
	public String cancelFlights(int flightDetailId) {
		if(flightDetailsRepository.existsById(flightDetailId)) {
			flightDetailsRepository.deleteById(flightDetailId);
			return "record deleted succesfully";
			
		}
		return "record not found";
	}


	@Override
	public List<FlightDetails> getByDate(LocalDateTime departure) {
		return flightDetailsRepository.findByDeparture(departure);
	}


	@Override
	public List<FlightDetails> viewAllFlightDetails(String flightId) {
		return flightDetailsRepository.findByFlightCode(flightId);
	}
	
	
	
	

}
