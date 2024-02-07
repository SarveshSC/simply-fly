package com.hexaware.simplyfly.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightDetailsDTO;
import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.service.IFlightDetailsService;

@RestController
@RequestMapping("/flightDetails")
public class FlightDetailsController {
	@Autowired
	IFlightDetailsService service;

	
	@PostMapping("/scheduleFlight")
	public FlightDetails scheduleFlight(@RequestBody FlightDetailsDTO flightDetailsDTO,@PathVariable String flightCode) {
		return service.scheduleFlight(flightDetailsDTO, flightCode);
	}
	
	@PutMapping("/rescheduleFlight")
	public FlightDetails rescheduleFlightDetails(FlightDetails flightDetails) {
		return service.rescheduleFlightDetails(flightDetails);
	}
	
	@DeleteMapping("/deleteFlight/{flightDetailId}")
	public String cancelFlights(int flightDetailId) {
		 return service.cancelFlights(flightDetailId);
	}
	
	@GetMapping("/getByDate/{departure}")
	public List<FlightDetails> getByDate(LocalDateTime departure){
		return service.getByDate(departure);
	}

	@GetMapping("/getAllFlightDetails/{flightId}")
	public  List<FlightDetails>  viewAllFlightDetails(String flightId){
		return service.viewAllFlightDetails(flightId);
	}


	
	
}
