package com.hexaware.simplyfly.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.service.IFlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flights")
public class FlightServiceController {
	@Autowired
	IFlightService service;
	
	
	@PostMapping("/addFlights/{airline}")
	public Flights addFlights(   @RequestBody  @Valid FlightDTO flightDto,@PathVariable String airline) throws AirlineNotFoundException
	{
		
		return service.addFlights(flightDto,  airline );
	}
	
	
	@PutMapping("/updateFlights/{airline}")
	public Flights updateFlights( @RequestBody @Valid FlightDTO flightDto,@PathVariable String airline) throws AirlineNotFoundException
	{
		
		return service.updateFlights(flightDto,  airline );
	}
	
	@DeleteMapping("/deleteFlights/{flightId}")
	public String removeFlights(@PathVariable String flightId) throws Exception {
		return service.removeFlights(flightId);
	}



}
