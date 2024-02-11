package com.hexaware.simplyfly.service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InvalidTimingException;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FlightTripServiceImpl implements IFlightTripService {

	@Autowired
	FlightTripRepository flightTripRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	Logger logger = LoggerFactory.getLogger(FlightTripServiceImpl.class);

	
	@Override
	public FlightTrip scheduleFlight(FlightTripDTO flightTripDTO,String flightCode,String sourceIata,String destinationIata) throws Exception {
		FlightTrip flightTrip=null;
		Flights flight=flightRepository.findById(flightCode).orElse(null);
	if(flight!=null)	
	{
		Airports source=airportRepository.findById(sourceIata).orElse(null);
		Airports destination=airportRepository.findById(destinationIata).orElse(null);
		if(source!=null && destination !=null )
		{
			if(source!=destination){
				if(validateForAddingDetails(flightTripDTO, flight, source, destination))	{
					flightTrip=new FlightTrip();
					flightTrip.setFlights(flightRepository.findById(flightCode).orElse(null));
					flightTrip.setArrival(flightTripDTO.getArrival());
					flightTrip.setDeparture(flightTripDTO.getDeparture());
					flightTrip.setFilledSeats(flightTripDTO.getFilledSeats());
					flightTrip.setTicketPrice(flightTripDTO.getTicketPrice());
					flightTrip.setFlights(flightRepository.findById(flightCode).orElse(null));
					flightTrip.setSource(source);
					flightTrip.setDestination(destination);
					flight.setLastArrivalTime(flightTripDTO.getArrival());
					flight.setLastArrivedAirportId(destination.getIataCode());
					 flightTripRepository.save(flightTrip);
				}
				return flightTrip;
			}
			else {
				throw new Exception("source and destination cannot be same");
			}
		
		}
		else
		{
			throw new AirportNotFoundException("invalid airport details");
		}
	}
	else {
		throw new FlightNotFoundException("invalid flight details");
	}
		
	}

//HERE ONLY TIMINGS CAN BE CHANGED THAT IS  ONLY PRICE AND SEATS
	@Override
	public FlightTrip rescheduleFlightTrip(FlightTrip flightTrip) throws Exception {
		Flights flight=(flightTripRepository.findById(flightTrip.getFlightTripId()).orElse(null)).getFlights();
		String FlightCode=flight.getFlightCode();
		if(flightRepository.existsById(FlightCode)) {
			flight.setLastArrivalTime(flightTrip.getArrival());
			return flightTripRepository.save(flightTrip);
		
		}
		
		else {
			throw new FlightNotFoundException("flight not exists");
		}

	}

	@Override
	public String cancelFlights(int flightDetailId) {
		if(flightTripRepository.existsById(flightDetailId)) {
			Flights flight=(flightTripRepository.findById(flightDetailId).orElse(null)).getFlights();
			String FlightCode=flight.getFlightCode();
			flightTripRepository.deleteById(flightDetailId);
			Optional<Integer> lastFlightdetail=flightTripRepository.findMaxFlightDetailId(FlightCode);

			Optional<FlightTrip> lastFlightTripOptional = lastFlightdetail.flatMap(integer -> flightTripRepository.findById(integer));

			FlightTrip lastFlightTrip = lastFlightTripOptional.orElse(null); // Or handle it accordingly if lastFlightTrip is null

				if(lastFlightTrip==null) {
					flight.setLastArrivalTime(null);
					flight.setLastArrivedAirportId(null);
				}
				else {
					flight.setLastArrivalTime(lastFlightTrip.getArrival());
					flight.setLastArrivedAirportId(lastFlightTrip.getDestination().getIataCode());
				}
				
			
			
			
			return "record deleted succesfully";
			
		}
		return "record not found";
	}


	@Override
	public List<FlightTrip> getByDate(LocalDate departure) {	
		return flightTripRepository.findByDeparture(departure);
	}


	@Override
	public List<FlightTrip> viewAllFlightTrip(String flightId) throws Exception {
		if(flightRepository.existsById(flightId))
			return flightTripRepository.findByFlightCode(flightId);
		else
			throw new Exception("flight not exists");
	}
	
	public boolean  validateForAddingDetails(FlightTripDTO flightTripDto,Flights flight,Airports source,Airports destination) throws Exception {

		if(flightTripDto.getDeparture().isBefore(flightTripDto.getArrival())) {
			if(flight.getLastArrivedAirportId()==null) {
				return true;
			}
			
			if(source.getIataCode().equals(flight.getLastArrivedAirportId())) {
				if(flight.getLastArrivalTime().isBefore(flightTripDto.getDeparture())) {
					LocalDateTime dateTime1=flight.getLastArrivalTime();
					LocalDateTime dateTime2=flightTripDto.getDeparture();
					Long duration=Duration.between(dateTime1, dateTime2).toHours();
					if(duration>2) {
						return true;
					}
					else {
						throw new Exception("the minimum duration between 2 trips is 2 hours ");
					}
					
				}
				else {
					throw new Exception("the previous arrival time is beyond the pressent departure time");
				}
				
			}
			else {
				throw new Exception("the previous arrived station must be same as the present departure station");
			}
			
		}
		else {
			throw new InvalidTimingException("enter valid timings");
		}
	}


	@Override
	public List<FlightTrip> viewFlightBySourceAndDestination(String sourceIata, String destinationIata) throws Exception {
		Airports source=airportRepository.findById(sourceIata).orElse(null);
		Airports destination=airportRepository.findById(destinationIata).orElse(null);
		if(source!=null && destination !=null ) {
			if(source!=(destination)) {
				return flightTripRepository.findBySourceAndDestination(sourceIata, destinationIata);
			}
			else {
				throw new Exception("source and destination cannot be same");
			}
		}
		else {
			throw new AirportNotFoundException("please enter valid airport details");
		}
	}
	
	@Override
	public List<FlightTrip> getByDateAndSourceDestination(LocalDate departure, String sourceIata, String destinationIata) throws Exception {
        List<FlightTrip> byDate = getByDate(departure);
        List<FlightTrip> bySourceDestination = viewFlightBySourceAndDestination(sourceIata, destinationIata);
        
        List<FlightTrip> combinedResult = new ArrayList<FlightTrip>(byDate);
        combinedResult.retainAll(bySourceDestination); // Retain only the common flights
        
        return combinedResult;
    }
	
	
}
