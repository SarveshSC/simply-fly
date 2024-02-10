package com.hexaware.simplyfly.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.PassengerDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.Passengers;
import com.hexaware.simplyfly.entities.PaymentStatus;
import com.hexaware.simplyfly.entities.Payments;
import com.hexaware.simplyfly.entities.SeatStatus;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.entities.Seats;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.CustomerNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InsufficientPassengersException;
import com.hexaware.simplyfly.exception.InvalidSeatException;
import com.hexaware.simplyfly.exception.SeatNotVacantException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;
import com.hexaware.simplyfly.repository.PassengerRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.SeatStructureRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	FlightTripRepository flightTripRepo;
	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	SeatRepository seatRepo;
	
	@Autowired
	SeatStructureRepository seatStructRepo;
	
	public Passengers setPassenger(PassengerDTO passengerDTO, FlightTrip flightTrip) throws InvalidSeatException {
		Passengers passenger = new Passengers();
		passenger.setName(passengerDTO.getName());
		passenger.setAge(passengerDTO.getAge());
		passenger.setGender(passengerDTO.getGender());
		SeatStructure seatNo = seatStructRepo.findById(passengerDTO.getSeat()).orElse(null);
		if(seatNo == null) {
			throw new InvalidSeatException();
		}
		Seats seat = new Seats(SeatStatus.Booked, flightTrip, seatNo);
		passenger.setSeat(seat);
		
		return passenger;
	}

	@Override
	public Bookings bookFlight(BookingDTO bookingDTO, String customerId) throws CustomerNotFoundException, SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException {
		Bookings booking = new Bookings();
		
		Customer customer = customerRepo.findById(customerId).orElse(null);
		if(customer == null) throw new CustomerNotFoundException("Customer with id " + customerId + " not found");
		
		booking.setCustomer(customer);
		
		
		booking.setBookingId(bookingDTO.getBookingId());
		FlightTrip flightTrip = flightTripRepo.findById(bookingDTO.getFlightTripId()).orElse(null);
		
		if(flightTrip == null) throw new FlightNotFoundException("Invalid Flight Credentials");
		
		booking.setFlightTripForBooking(flightTrip);
		booking.setBookingDateTime(LocalDateTime.now());
		
		
		
		
		Set<PassengerDTO> passengersDTO = bookingDTO.getPassengers();
		if(passengersDTO == null) throw new InsufficientPassengersException();
		Set<Passengers> passengers = new HashSet<>();
		for(PassengerDTO p : passengersDTO) {
			Seats newSeat = new Seats();
			newSeat.setFlightTripId(flightTrip);
			SeatStatus status = seatRepo.getSeatStatus(p.getSeat(), flightTrip.getFlightTripId());
			if(status == SeatStatus.Booked) throw new SeatNotVacantException(p.getSeat());
			
			
			newSeat.setStatus(SeatStatus.Booked);
			
			Passengers passenger = setPassenger(p, flightTrip);
			newSeat.setSeatNo(passenger.getSeat().getSeatNo());
			
			seatRepo.save(newSeat);
			
			passengers.add(passenger);
		}
		passengerRepo.saveAll(passengers);
		booking.setPassengers(passengers);
		
		
////		System.out.println(bookingDTO.getBookingId());
//
//		bookingRepo.save(booking);
//		
//		
		double ticketPrice = flightTrip.getTicketPrice();
////		System.out.println(ticketPrice);
//
		booking.setAmount(ticketPrice * bookingDTO.getPassengers().size());
//		
//		//create new payment object set status and bookingId
		Set<Payments> payments = booking.getPayments();
		Payments paymentTransaction = new Payments();
		paymentTransaction.setPaymentBookings(booking);
		paymentTransaction.setStatus(PaymentStatus.Completed);
		payments.add(paymentTransaction);
		
//		
		booking.setPayments(payments);
		

		return bookingRepo.save(booking);
	}

	@Override
	public List<Bookings> getAllBookingsByUsername(String username) {
		return bookingRepo.findAllByUsername(username);
	}

	@Override
	public String cancelBooking(Integer bookingId) throws BookingNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
