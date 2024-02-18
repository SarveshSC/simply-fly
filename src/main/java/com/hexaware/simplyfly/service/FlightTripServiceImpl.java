package com.hexaware.simplyfly.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.FlightTripDTO;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.FlightTripStatus;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.exception.InvalidTimingException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;
import com.hexaware.simplyfly.repository.UserRepository;

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

	@Autowired
	IBookingService bookingService;
	
	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(FlightTripServiceImpl.class);

	@Override
	public FlightTrip scheduleFlight(FlightTripDTO flightTripDTO, String flightCode, String sourceIata,
			String destinationIata,String username) throws Exception {
		FlightTrip flightTrip = null;
		Flights flight = flightRepository.findById(flightCode).orElse(null);
		//if(flightTripRepository.existsById(flightTripDTO.getFlightTripId()))throw new Exception("flight details  already exists");
		User user=userRepository.findById(username).orElseThrow(()->new UserNotFoundException(username));
		if (flight != null && flight.getAirline()==user.getAirline()) {
			Airports source = airportRepository.findById(sourceIata).orElse(null);
			Airports destination = airportRepository.findById(destinationIata).orElse(null);
			if (source != null && destination != null) {
				if (source != destination) {
					if (validateForAddingDetails(flightTripDTO, flight, source, destination)) {
						flightTrip = new FlightTrip();
						flightTrip.setFlights(flightRepository.findById(flightCode).orElse(null));
						flightTrip.setArrival(flightTripDTO.getArrival());
						flightTrip.setDeparture(flightTripDTO.getDeparture());
						flightTrip.setTicketPrice(flightTripDTO.getTicketPrice());
						flightTrip.setFlights(flightRepository.findById(flightCode).orElse(null));
						flightTrip.setSource(source);
						flightTrip.setDestination(destination);
						flightTrip.setStatus(FlightTripStatus.Running);
						flight.setLastArrivalTime(flightTripDTO.getArrival());
						flight.setLastArrivedAirportId(destination.getIataCode());
						flightTripRepository.save(flightTrip);
					}
					return flightTrip;
				} else {
					throw new Exception("source and destination cannot be same");
				}

			} else {
				throw new AirportNotFoundException(source + " or " + destination);
			}
		} else {
			throw new FlightNotFoundException(flight.getFlightCode().toString());
		}

	}

//HERE ONLY TIMINGS CAN BE CHANGED THAT IS  ONLY PRICE AND SEATS
	@Override
	public FlightTrip rescheduleFlightTrip(FlightTrip flightTrip,String username) throws Exception {
		
		Flights flight = (flightTripRepository.findById(flightTrip.getFlightTripId()).orElseThrow(()->new Exception("no such flighttrip exists"))).getFlights();
		String presentStatus=(flightTripRepository.findById(flightTrip.getFlightTripId()).orElseThrow(()->new Exception("no such flighttrip exists"))).getStatus().toString();
		String flightCode = flight.getFlightCode();
		User user=userRepository.findById(username).orElseThrow(()->new UserNotFoundException(username));
		if(flightRepository.existsById(flightCode) && presentStatus.equals(FlightTripStatus.Running.toString())
				&&flight.getAirline().equals(user.getAirline())) {
			flight.setLastArrivalTime(flightTrip.getArrival());
			return flightTripRepository.save(flightTrip);

		}

		else {
			throw new FlightNotFoundException(flightTrip.getFlightTripId().toString());
		}

	}

	@Override
	public String cancelFlights(Integer flightTripId,String username) throws BookingNotFoundException, InvalidFlightException, UserNotFoundException {
		FlightTrip flightTrip = flightTripRepository.findById(flightTripId).orElse(null);
		User user=userRepository.findById(username).orElseThrow(()->new UserNotFoundException(username));
		
		if (flightTrip == null || flightTrip.getFlights().getAirline()!=user.getAirline()) {
			throw new InvalidFlightException(flightTripId.toString());
		}
		System.out.println(flightTrip.getBookings());
logger.info("in progress of deleting");

		Set<Bookings> Bookings = flightTrip.getBookings();
		for (Bookings booking : Bookings) {
			bookingService.cancelBooking(booking.getBookingId(), booking.getCustomer().getUsername());
		}

		Flights flight = flightTrip.getFlights();
		String flightCode = flight.getFlightCode();
    flightTrip.setStatus(FlightTripStatus.Cancelled);
		Optional<Integer> lastFlightdetail = flightTripRepository.findMaxFlightDetailId(flightCode);
		if(lastFlightdetail!=null)
		{
			Optional<FlightTrip> lastFlightTripOptional = lastFlightdetail
					.flatMap(integer -> flightTripRepository.findById(integer));
		
		
		FlightTrip lastFlightTrip = lastFlightTripOptional.orElse(null); // Or handle it accordingly if
																			// lastFlightTrip is null

		if (lastFlightTrip == null) {
			flight.setLastArrivalTime(null);
			flight.setLastArrivedAirportId(null);
		} else {
			flight.setLastArrivalTime(lastFlightTrip.getArrival());
			flight.setLastArrivedAirportId(lastFlightTrip.getDestination().getIataCode());
		}
		}
		else {
			flight.setLastArrivalTime(null);
			flight.setLastArrivedAirportId(null);
		}

		return "Flight has been cancelled sorry for the incovenience";
	}

	@Override
	public List<FlightTrip> getByDate(LocalDate departure) {
		return flightTripRepository.findByDeparture(departure);
	}

	@Override
	public List<FlightTrip> viewAllFlightTrip(String flightId) throws Exception {
		if (flightRepository.existsById(flightId))
			return flightTripRepository.findByFlightCode(flightId);
		else
			throw new Exception("flight not exists");
	}

	public boolean validateForAddingDetails(FlightTripDTO flightTripDto, Flights flight, Airports source,
			Airports destination) throws Exception {

		if (flightTripDto.getDeparture().isBefore(flightTripDto.getArrival())) {
			if (flight.getLastArrivedAirportId() == null) {
				return true;
			}

			if (source.getIataCode().equals(flight.getLastArrivedAirportId())) {
				if (flight.getLastArrivalTime().isBefore(flightTripDto.getDeparture())) {
					LocalDateTime dateTime1 = flight.getLastArrivalTime();
					LocalDateTime dateTime2 = flightTripDto.getDeparture();
					Long duration = Duration.between(dateTime1, dateTime2).toHours();
					if (duration > 2) {
						return true;
					} else {
						throw new Exception("The minimum duration between 2 trips is 2 hours.");
					}

				} else {
					throw new Exception("The previous arrival time is beyond the pressent departure time.");
				}

			} else {
				throw new Exception("The previous arrived station must be same as the present departure station.");
			}

		} else {
			throw new InvalidTimingException("Please enter valid timings");
		}
	}

	@Override
	public List<FlightTrip> viewFlightBySourceAndDestination(String sourceIata, String destinationIata)
			throws Exception {
		Airports source = airportRepository.findById(sourceIata).orElse(null);
		Airports destination = airportRepository.findById(destinationIata).orElse(null);
		if (source != null && destination != null) {
			if (source != (destination)) {
				return flightTripRepository.findBySourceAndDestination(sourceIata, destinationIata);
			} else {
				throw new Exception("Source and Destination cannot be same.");
			}
		} else {
			throw new AirportNotFoundException(sourceIata + " or " + destinationIata);
		}
	}

	@Override
	public List<FlightTrip> getByDateAndSourceDestination(LocalDate departure, String sourceIata,
			String destinationIata) throws Exception {
		List<FlightTrip> byDate = getByDate(departure);
		List<FlightTrip> bySourceDestination = viewFlightBySourceAndDestination(sourceIata, destinationIata);

		List<FlightTrip> combinedResult = new ArrayList<>(byDate);
		combinedResult.retainAll(bySourceDestination); // Retain only the common flights

		return combinedResult;
	}

}
