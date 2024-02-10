package com.hexaware.simplyfly.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.service.IFlightTripService;

@RestController
@RequestMapping("/flightTrips")
public class FlightTripsController {
	@Autowired
	IFlightTripService service;
	Logger logger = LoggerFactory.getLogger(FlightTripsController.class);

	
	@PostMapping("/scheduleFlight/{flightCode}/{sourceIata}/{destinationIata}")
	public FlightTrip scheduleFlight(@RequestBody FlightTripDTO flightTripsDTO,@PathVariable String flightCode, @PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception {
		return service.scheduleFlight(flightTripsDTO, flightCode,sourceIata,destinationIata);
	}
	
	@PutMapping("/rescheduleFlight")
	public FlightTrip rescheduleFlightDetails(@RequestBody FlightTrip flightTrips) throws Exception {
		return service.rescheduleFlightTrip(flightTrips);
	}
	
	@DeleteMapping("/cancelFlight/{flightTripId}")
	public String cancelFlights(@PathVariable int flightTripId) {
		 return service.cancelFlights(flightTripId);
	}
	
	@GetMapping("/getByDate/{departure}")
	public List<FlightTrip> getByDate(@PathVariable String departure){
		return service.getByDate(LocalDate.parse(departure));
	}

		@GetMapping("/getAllFlightDetails/{flightId}")
		public  List<FlightTrip>  viewAllFlightDetails(@PathVariable String flightId) throws Exception{
			logger.info("Flight ID: {}", flightId);
			return service.viewAllFlightTrip(flightId);
		}
		
		@GetMapping("/getFlightDetailsBySourceAndDestination/{sourceIata}/{destinationIata}")
		public List<FlightTrip> viewFlightBySourceAndDestination(@PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception
		{
			return service.viewFlightBySourceAndDestination(sourceIata, destinationIata);
		}
		
		@GetMapping("/getFlightDetaisBySourceDestinationDate/{departure}/{sourceIata}/{destinationIata}")
		public List<FlightTrip> getByDateAndSourceDestination(@PathVariable String departure, @PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception {
			
				return service.getByDateAndSourceDestination(LocalDate.parse(departure), sourceIata, destinationIata);

			}
	


	
	
}