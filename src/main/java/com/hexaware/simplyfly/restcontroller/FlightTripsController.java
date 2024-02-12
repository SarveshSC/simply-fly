package com.hexaware.simplyfly.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.service.IFlightTripService;

@RestController
@RequestMapping("/simply-fly/flightTrips")
public class FlightTripsController {
	@Autowired
	IFlightTripService service;
	
	Logger logger = LoggerFactory.getLogger(FlightTripsController.class);

	

	@PostMapping("/schedule-flight/{flightCode}/{sourceIata}/{destinationIata}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public FlightTrip scheduleFlight(@RequestBody FlightTripDTO flightTripsDTO,@PathVariable String flightCode, @PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception {
		return service.scheduleFlight(flightTripsDTO, flightCode,sourceIata,destinationIata);
	}
	

	@PutMapping("/reschedule-flight")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public FlightTrip rescheduleFlightDetails(@RequestBody FlightTrip flightTrips) throws Exception {
		return service.rescheduleFlightTrip(flightTrips);
	}
	

	@DeleteMapping("/cancel-flight/{flightTripId}")
  	@PreAuthorize("hasAuthority('FlightOwner')")
	public String cancelFlights(@PathVariable Integer flightTripId) throws BookingNotFoundException, InvalidFlightException {
		 return service.cancelFlights(flightTripId);
	}
	

		@GetMapping("/get-by-date/{departure}")
	@PreAuthorize("hasAuthority('FlightOwner','Customer')")
	public List<FlightTrip> getByDate(@PathVariable String departure){
		return service.getByDate(LocalDate.parse(departure));
	}


		@GetMapping("/get-all-flight-details/{flightId}")
		@PreAuthorize("hasAuthority('FlightOwner')")
		public  List<FlightTrip>  viewAllFlightDetails(@PathVariable String flightId) throws Exception{
			logger.info("Flight ID: {}", flightId);
			return service.viewAllFlightTrip(flightId);
		}
		

		@GetMapping("/search-flights-by-source-and-destination/{sourceIata}/{destinationIata}")
		@PreAuthorize("hasAuthority('FlightOwner','Customer','Admin')")
		public List<FlightTrip> viewFlightBySourceAndDestination(@PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception
		{
			return service.viewFlightBySourceAndDestination(sourceIata, destinationIata);
		}

		@GetMapping("/search-flights-by-date-source-destination/{departure}/{sourceIata}/{destinationIata}")
		@PreAuthorize("hasAuthority('FlightOwner','Customer','Admin')")
		public List<FlightTrip> getByDateAndSourceDestination(@PathVariable String departure, @PathVariable String sourceIata,@PathVariable String destinationIata) throws Exception {
			
				return service.getByDateAndSourceDestination(LocalDate.parse(departure), sourceIata, destinationIata);

			}
	


	
	
}
