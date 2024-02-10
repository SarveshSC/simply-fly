package com.hexaware.simplyfly.restcontroller;

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

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.exception.CustomerNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InsufficientPassengersException;
import com.hexaware.simplyfly.exception.InvalidSeatException;
import com.hexaware.simplyfly.exception.SeatNotVacantException;
import com.hexaware.simplyfly.service.IBookingService;
import com.hexaware.simplyfly.service.ICustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	
	Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IBookingService bookingService;
	
	@PostMapping("/createAccount")
	public Customer createAccount(@RequestBody Customer customer) {
		return customerService.createAccount(customer);
	}
	
	@PutMapping("/updateAccount")
	public Customer updateAccount(@RequestBody Customer customer) {
		return customerService.editAccountInfo(customer);
	}
	
	@DeleteMapping("/deleteAccount/{username}")
	public String deleteAccount(@PathVariable String username) {
		return customerService.deleteAccount(username);
	}	
	
	@PostMapping("/booking/book-flight/{username}")
	public Bookings bookFlight(@RequestBody BookingDTO bookingDTO, @PathVariable String username) throws CustomerNotFoundException, SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException {
		return bookingService.bookFlight(bookingDTO, username);
	}
	
	@GetMapping("booking/get-by-customer/{username}")
	public List<Bookings> getBookingByUsername(@PathVariable String username){
		return  bookingService.getAllBookingsByUsername(username);
	}
}
