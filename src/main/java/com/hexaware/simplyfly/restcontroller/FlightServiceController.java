package com.hexaware.simplyfly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightsDto;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.service.IFlightService;

@RestController
@RequestMapping("/flights")
public class FlightServiceController {
	@Autowired
	IFlightService service;
	
	
	@PostMapping("/addFlights/{sourceIata}/{destinationIata}/{airline}")
	public Flights addFlights(@RequestBody FlightsDto flightDto,@PathVariable String sourceIata,@PathVariable String destinationIata,@PathVariable String airline)
	{
		
		return service.addFlights(flightDto, sourceIata, destinationIata, airline );
	}
	
	
	@PutMapping("/addFlights/{sourceIata}/{destinationIata}/{airline}")
	public Flights updateFlights(@RequestBody FlightsDto flightDto,@PathVariable String sourceIata,@PathVariable String destinationIata,@PathVariable String airline)
	{
		
		return service.updateFlights(flightDto, sourceIata, destinationIata, airline );
	}
	
	@DeleteMapping("/deleteFlights/{flightId}")
	public String removeFlights(String flightId) {
		return service.removeFlights(flightId);
	}
	
	@GetMapping("/getBySource/{sourceIata}")
	public List<Flights> flightsBySource(String sourceIata){
		return service.flightsBySource(sourceIata);
	}
	
	@GetMapping("/getBySource/{destinationIata}")
	public List<Flights> flightsByDesstination(String destinationIata){
		return service.flightsByDestination(destinationIata);
	}



}
