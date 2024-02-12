package com.hexaware.simplyfly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/flights")
public class FlightServiceController {
	@Autowired
	IFlightService service;

	@PostMapping("/addFlights/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public Flights addFlights(@RequestBody @Valid FlightDTO flightDto, @PathVariable String username) throws Exception {
		return service.addFlights(flightDto, username);
	}

	@PutMapping("/updateFlights/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public Flights updateFlights(@RequestBody @Valid FlightDTO flightDto, @PathVariable String username) throws Exception {

		return service.updateFlights(flightDto, username);
	}

	@DeleteMapping("/deleteFlights/{username}/{flightId}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public String removeFlights(@PathVariable String username, @PathVariable String flightId) throws Exception {
		return service.removeFlights(flightId, username);
	}
	
	@GetMapping("/getFlightsByAirline/{airlineId}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<Flights> getFlightsByAirlineId(@PathVariable String airlineId) throws AirlineNotFoundException
	{
		return service.viewAllFlightsByAirlineId(airlineId);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<Flights> getAllFlights(){
		return service.viewAllFlights();
	}

}
