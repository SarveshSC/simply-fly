package com.hexaware.simplyfly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.repository.AirlineRepository;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;

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
	FlightTripRepository flightDetailsRepo;

	@Override
	public Flights addFlights(FlightDTO flightDto,String airlineId) throws AirlineNotFoundException {
		Flights flight=null;
		Airlines airline=airLineRepo.findById(airlineId).orElse(null);
		if(airline!=null)
		{
			flight=new Flights();
			flight.setFlightCode(flightDto.getFlightCode());
			flight.setTotalSeats(flightDto.getTotalSeats());
			flight.setCabinWeight(flightDto.getCabinWeight());
			flight.setCheckInWeight(flightDto.getCheckInWeight());
			flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
			return flightRepo.save(  flight);	
		}
		else {
			throw new AirlineNotFoundException("the airline doesnot exists");
		}
	}

	@Override
	public Flights updateFlights(FlightDTO flightDto,String airlineId) throws AirlineNotFoundException {
		Flights flight=null;
		if(airLineRepo.findById(airlineId)!=null)
		{
			flight=new Flights();
			flight.setFlightCode(flightDto.getFlightCode());
			flight.setTotalSeats(flightDto.getTotalSeats());
			flight.setCabinWeight(flightDto.getCabinWeight());
			flight.setCheckInWeight(flightDto.getCheckInWeight());
			flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
			return flightRepo.save(flight);	
		}
		else {
			throw new AirlineNotFoundException("the airline doesnot exists");
		}
		
	}

	@Override
	public String removeFlights(String flightId) throws Exception {
		if(flightRepo.existsById(flightId)) {
			Flights flight=flightRepo.findById(flightId).orElse(null);
			if(flight.getFlightTrip().size()==0) {
			flightRepo.deleteById(flightId);
			return "record deleted";
			}
			else {
				throw new Exception("this flight has more than 1 schedule, cannot be deleted now");
			}
		}
		return "record not found";
	}
	
	@ExceptionHandler({AirlineNotFoundException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "airline not found")
	public ResponseEntity<String> airlineNotFound(AirlineNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	
}
