package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.simplyfly.dto.BookingsDTO;
import com.hexaware.simplyfly.entities.BookingStatus;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Passengers;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.repository.BookingRepository;

public class BookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository bookingRepo;

	@Override
	public Bookings bookFlight(BookingsDTO dto, Set<Passengers> passengers) {
		Bookings booking = new Bookings();
		booking.setBookingId(dto.getBookingId());
		booking.setBookingDateTime(dto.getBookingDateTime());
		booking.setAmount(dto.getAmount());
		booking.setStatus(BookingStatus.Booked);
		booking.setPassengers(passengers);
		
		return bookingRepo.save(booking);
	}

	@Override
	public List<Bookings> getAllBookingsByUsername(String username) {
		return bookingRepo.findAllByUsername(username);
	}
	
	//validation from user yet to be implemented.
	public List<Bookings> getAllBookings(){
		return bookingRepo.findAll();
	}

	@Override
	public String cancelBooking(Integer bookingId) throws BookingNotFoundException{
		String str = "";
		Bookings booking = bookingRepo.findById(bookingId).orElse(null);
		
		if(booking == null) throw new BookingNotFoundException("No Booking with this id found");
		else {
			booking.setStatus(BookingStatus.Cancelled);
			str = "Booking Cancelled";
		}		
		return str;
	}

}
