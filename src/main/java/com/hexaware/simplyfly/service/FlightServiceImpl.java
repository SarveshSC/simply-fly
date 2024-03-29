package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.FlightStaus;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.FlightScheduledExcpetion;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.AirlineRepository;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements IFlightService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	FlightRepository flightRepo;

	@Autowired
	AirlineRepository airLineRepo;

	@Autowired
	AirportRepository airPortRepo;

	@Override
	public Flights addFlights(FlightDTO flightDto, String username) throws Exception {
		Flights flight = null;
		User user = userRepo.findUserByUsername(username);
		if(user == null) throw new UserNotFoundException(username);
		if(flightRepo.existsById(flightDto.getFlightCode())) throw new Exception("flight already exists");
		String airlineId = user.getAirline().getAirlineId();
		Airlines airline = airLineRepo.findById(airlineId).orElse(null);
		if (airline != null && user.getAirline().getAirlineId().equals(flightDto.getAirlineId())) {
			flight = new Flights();
			flight.setFlightCode(flightDto.getFlightCode());
			flight.setTotalSeats(flightDto.getTotalSeats());
			flight.setCabinWeight(flightDto.getCabinWeight());
			flight.setCheckInWeight(flightDto.getCheckInWeight());
			flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
			flight.setFlightStaus(FlightStaus.Active);
			return flightRepo.save(flight);
		} else {
			throw new AirlineNotFoundException(flightDto.getAirlineId());
		}
	}

	@Override
	public Flights updateFlights(FlightDTO flightDto, String username) throws Exception {
		Flights flight = null;
		if(!flightRepo.existsById(flightDto.getFlightCode())) throw new Exception("flight not exists");
		User user = userRepo.findById(username).orElseThrow(()->new UserNotFoundException(username));
		String airlineId = user.getAirline().getAirlineId();
		Airlines airline = airLineRepo.findById(airlineId).orElse(null);
		if (airline != null && user.getAirline().getAirlineId().equals(flightDto.getAirlineId())) {
			flight = flightRepo.findById(flightDto.getFlightCode()).orElseThrow(()->new FlightNotFoundException(flightDto.getFlightCode()));
			flight.setFlightCode(flightDto.getFlightCode());
			flight.setTotalSeats(flightDto.getTotalSeats());
			flight.setCabinWeight(flightDto.getCabinWeight());
			flight.setCheckInWeight(flightDto.getCheckInWeight());
			flight.setAirline(airLineRepo.findById(airlineId).orElse(null));
			flight.setFlightStaus(flightDto.getFlightStatus());
			return flightRepo.save(flight);
		} else {
			throw new AirlineNotFoundException(airlineId);
		}

	}

	@Override
	public String removeFlights(String flightId, String username) throws AirlineNotFoundException, UserNotFoundException, FlightNotFoundException, FlightScheduledExcpetion {
		User user = userRepo.findUserByUsername(username);
		if(user == null) throw new UserNotFoundException(username);
		
		String airlineId = user.getAirline().getAirlineId();
		Airlines airline=airLineRepo.findById(airlineId).orElse(null);
		
		Set<Flights> flights = airline.getFlights();
		
		Flights flight = flightRepo.findById(flightId).orElse(null);
		if(!flights.contains(flight)) {
			throw new FlightNotFoundException(flightId);
		}
		
			flight.setFlightStaus(FlightStaus.Inactive);
			return "Flight deleted";
		
		
	}
	
	@Override
	public List<FlightDTO> viewAllFlightsByUsername(String username) throws AirlineNotFoundException {
		String airlineId=userRepo.getById(username).getAirline().getAirlineId();
		if(airLineRepo.existsById(airlineId)) {
			List<Flights> flights= flightRepo.findByAirline(airlineId);
			return flights.stream()
		            .map(flight -> new FlightDTO(
		                flight.getFlightCode(),
		                flight.getTotalSeats(),
		                flight.getCheckInWeight(),
		                flight.getCabinWeight(),
		                flight.getAirline().getAirlineId(),
		                flight.getLastArrivedAirportId(),
		                flight.getLastArrivalTime(),
		                flight.getFlightStaus()
		            ))
		            .collect(Collectors.toList());
		}
		else {
			throw new AirlineNotFoundException(airlineId);
		}
	}
	


	@Override
	public List<Flights> viewAllFlights() {
		return flightRepo.findAll();
	}

	@ExceptionHandler({FlightNotFoundException.class, AirlineNotFoundException.class })
	// @ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "airline not found")
	public ResponseEntity<String> airlineNotFound(AirlineNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
