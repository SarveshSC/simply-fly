package com.hexaware.simplyfly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingsDTO;
import com.hexaware.simplyfly.entities.BookingStatus;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	BookingRepository bookingRepo;

	@Override
	public Customer createAccount(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public Customer editAccountInfo(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public String deleteAccount(String username) {
		custRepo.deleteById(username);
		
		return "Account Deleted";
	}

	@Override
	public Bookings bookFlight(BookingsDTO dto) {
		Bookings booking = new Bookings();
		booking.setBookingId(dto.getBookingId());
		booking.setBookingDateTime(dto.getBookingDateTime());
		booking.setAmount(dto.getAmount());
		booking.setStatus(BookingStatus.Booked);
		
		return bookingRepo.save(booking);
	}
	
	public List<Bookings> getAllBookings(String username){
		List<Bookings> list = new ArrayList<>();
		
		return list;
	}

	@Override
	public String cancelBooking(Integer bookingId) {
		String str = "";
		Bookings booking = bookingRepo.findById(bookingId).orElse(null);
		
		if(booking == null) str = "No bookings found";
		else {
			booking.setStatus(BookingStatus.Cancelled);
			str = "Booking Cancelled";
		}		
		return str;
	}

}
