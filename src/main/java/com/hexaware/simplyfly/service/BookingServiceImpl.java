package com.hexaware.simplyfly.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.PassengerDTO;
import com.hexaware.simplyfly.entities.BookingStatus;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.FlightTripStatus;
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
import com.hexaware.simplyfly.repository.PaymentRepository;
import com.hexaware.simplyfly.repository.SeatRepository;
import com.hexaware.simplyfly.repository.SeatStructureRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {
	
	Logger logger=LoggerFactory.getLogger(BookingServiceImpl.class);

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

	@Autowired
	PaymentRepository paymentRepo;

	@Override
	public Bookings bookFlight(BookingDTO bookingDTO, String customerId) throws CustomerNotFoundException,
			SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException {
		Bookings booking = new Bookings();
		logger.info("trying to book tickets for customerid "+customerId);
		Customer customer = customerRepo.findById(customerId).orElse(null);
		if (customer == null)
			throw new CustomerNotFoundException(customerId);

		booking.setCustomer(customer);

		booking.setBookingId(bookingDTO.getBookingId());
		FlightTrip flightTrip = flightTripRepo.findById(bookingDTO.getFlightTripId()).orElse(null);


		if (flightTrip == null || flightTrip.getStatus().equals(FlightTripStatus.Cancelled))
			throw new FlightNotFoundException(bookingDTO.getFlightTripId().toString());


		booking.setFlightTripForBooking(flightTrip);
		booking.setBookingDateTime(LocalDateTime.now());

		Set<PassengerDTO> passengersDTO = bookingDTO.getPassengers();
		if (passengersDTO == null)
			throw new InsufficientPassengersException();
		Set<Passengers> passengers = new HashSet<>();
		for (PassengerDTO p : passengersDTO) {
			Seats newSeat = new Seats();
			newSeat.setFlightTripId(flightTrip);
			SeatStatus status = seatRepo.getSeatStatus(p.getSeat(), flightTrip.getFlightTripId());
			if (status == SeatStatus.Booked)
				throw new SeatNotVacantException(p.getSeat());

			newSeat.setStatus(SeatStatus.Booked);

			Passengers passenger = setPassenger(p, flightTrip);
			booking.getPassengers().add(passenger);
			
//			newSeat.setSeatNo(passenger.getSeat().getSeatNo());
//
//			seatRepo.save(newSeat);

			passengers.add(passenger);
			passengerRepo.save(passenger);
		}
		flightTrip.setFilledSeats(flightTrip.getFilledSeats() + passengers.size());
		flightTripRepo.save(flightTrip);
		
		double ticketPrice = flightTrip.getTicketPrice();
		booking.setAmount(ticketPrice * bookingDTO.getPassengers().size());
		
		//create new payment object set status and bookingId
		Set<Payments> payments = addPayments(booking, PaymentStatus.Completed, bookingDTO.getPaymentId());

		booking.setPayments(payments);

		return bookingRepo.save(booking);
	}

	@Override
	public List<Bookings> getAllBookingsByUsername(String username) {
		return bookingRepo.findAllByUsername(username);
	}

	@Override
	public String cancelBooking(Integer bookingId, String customerId) throws BookingNotFoundException {
		logger.info("trying to cancel tickets for bookingid"+bookingId);
		List<Bookings> listOfBookings = getAllBookingsByUsername(customerId);
		Bookings booking = bookingRepo.findById(bookingId).orElse(null);
		if ((booking == null) || (!listOfBookings.contains(booking))) {
			logger.info("booking not found");
			throw new BookingNotFoundException(bookingId.toString());
		}
		if(booking.getStatus()==BookingStatus.Cancelled) {
			return ("Booking Cancelled");
		}
		
		String paymentId = paymentRepo.getPaymentId(bookingId);
		
		Set<Passengers> passengers = booking.getPassengers();
		// remove passengers?? or set seat mapping to null
		// remove from seats table
		
		for (Passengers p : passengers) {
			logger.info("trying to get the seat");
			seatRepo.delete(p.getSeat());
			logger.info("we got the seat");
//			p.getSeat().setStatus(SeatStatus.Vacant);
			p.setSeat(null);
		}
//		passengerRepo.delete(p);
		
		// set payment status refunded
		Set<Payments> payments = addPayments(booking, PaymentStatus.Refunded, paymentId);

		booking.setPayments(payments);

		// set status cancelled
		booking.setStatus(BookingStatus.Cancelled);
		logger.info("changing the status to cancelled");
		FlightTrip flightTrip = booking.getFlightTripForBooking();
		flightTrip.setFilledSeats(flightTrip.getFilledSeats() - passengers.size());
		logger.info("Booking Cancelled, Payment Refund in process");
		bookingRepo.save(booking);
		return "Booking Cancelled, Payment Refund in process";
	}

	public Passengers setPassenger(PassengerDTO passengerDTO, FlightTrip flightTrip) throws InvalidSeatException {
		System.out.println("set passenger called");
		Passengers passenger = new Passengers();
		passenger.setName(passengerDTO.getName());
		passenger.setAge(passengerDTO.getAge());
		passenger.setGender(passengerDTO.getGender());
		SeatStructure seatNo = seatStructRepo.findById(passengerDTO.getSeat()).orElse(null);
		if (seatNo == null) {
			throw new InvalidSeatException();
		}
		Seats seat = new Seats(SeatStatus.Booked, flightTrip, seatNo);
//		seatRepo.save(seat);
		passenger.setSeat(seat);

		return passenger;
	}

	public Set<Payments> addPayments(Bookings booking, PaymentStatus status, String paymentId) {
		Set<Payments> payments = booking.getPayments();
		Payments paymentTransaction = new Payments();
		paymentTransaction.setPaymentId(paymentId);
		paymentTransaction.setPaymentBookings(booking);
		paymentTransaction.setStatus(status);
		payments.add(paymentTransaction);

		return payments;
	}
	
	public List<Bookings> getAllBookings(){
		return bookingRepo.findAll();
	}
	
	public String getMaxId() {
		return passengerRepo.getMaxPassengerId();
	}
}
