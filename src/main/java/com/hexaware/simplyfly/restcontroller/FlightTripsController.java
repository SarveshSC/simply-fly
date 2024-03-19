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

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.service.IFlightTripService;

@RestController
@RequestMapping("/simply-fly/flightTrips")
public class FlightTripsController {
	@Autowired
	IFlightTripService service;

	Logger logger = LoggerFactory.getLogger(FlightTripsController.class);

	@PostMapping("/schedule-flight/{flightCode}/{sourceIata}/{destinationIata}/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public FlightTrip scheduleFlight(@RequestBody FlightTripDTO flightTripsDTO, @PathVariable String flightCode,
			@PathVariable String sourceIata, @PathVariable String destinationIata, @PathVariable String username)
			throws Exception {
		logger.info("trying to add a schedule");
		return service.scheduleFlight(flightTripsDTO, flightCode, sourceIata, destinationIata, username);
	}

	@PutMapping("/reschedule-flight/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")

	public FlightTrip rescheduleFlightDetails(@RequestBody FlightTripDTO flightTrips, @PathVariable String username)
			throws Exception {
		return service.rescheduleFlightTrip(flightTrips, username);

	}

	@DeleteMapping("/cancel-flight/{flightTripId}/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public String cancelFlights(@PathVariable Integer flightTripId, @PathVariable String username)
			throws BookingNotFoundException, InvalidFlightException, UserNotFoundException {
		return service.cancelFlights(flightTripId, username);
	}

	@GetMapping("/get-by-date/{departure}")
	@PreAuthorize("hasAnyAuthority('FlightOwner','Customer')")
	public List<FlightTrip> getByDate(@PathVariable String departure) {
		return service.getByDate(LocalDate.parse(departure));
	}

	@GetMapping("/get-all-flight-details/{flightId}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<FlightTripDTO> viewAllFlightDetails(@PathVariable String flightId) throws Exception {
		logger.info("Flight ID: {}", flightId);
		return service.viewAllFlightTrip(flightId);
	}


	@GetMapping("/search-flights-by-source-and-destination/{sourceIata}/{destinationIata}")
	@PreAuthorize("hasAnyAuthority('FlightOwner','Customer','Admin')")
	public List<FlightTrip> viewFlightBySourceAndDestination(@PathVariable String sourceIata,
			@PathVariable String destinationIata) throws Exception {
		return service.viewFlightBySourceAndDestination(sourceIata, destinationIata);
	}


	@GetMapping("/search-flights-by-date-source-destination/{departure}/{sourceIata}/{destinationIata}")
	public List<FlightTrip> getByDateAndSourceDestination(@PathVariable String departure,
			@PathVariable String sourceIata, @PathVariable String destinationIata) throws Exception {

		return service.getByDateAndSourceDestination(LocalDate.parse(departure), sourceIata, destinationIata);

	}
//
//	@GetMapping("/list-all-trips")
//	@PreAuthorize("hasAuthority('FlightOwner')")
//	public List<FlightTrip> listAllTrips(){
//		return service.listAllTrips();
//	}
	


		@GetMapping("/get-flightTrips-by-username/{username}")
		@PreAuthorize("hasAuthority('FlightOwner')")
		public List<FlightTripDTO> getAllFlightsByUsername(@PathVariable String username) throws UserNotFoundException {
			return service.getAllFlightsByUsername(username);
		}

	@GetMapping("/get-bookings-by-flightTripId/{username}/{flightTripId}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<BookingDTO> getAllBookingsByFlightTripId(@PathVariable int flightTripId,@PathVariable String username) throws Exception {
		return service.getAllBookingsByFlightTripId(flightTripId, username);
	}
	
	
	@GetMapping("/get-bookings-by-username/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<BookingDTO> getAllBookingsByUsername(@PathVariable String username) throws UserNotFoundException{
		return service.getAllBookingsByUsername(username);
	}

	

	

}
