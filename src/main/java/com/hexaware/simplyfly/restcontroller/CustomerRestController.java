package com.hexaware.simplyfly.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.AuthRequest;
import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.CustomerNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InsufficientPassengersException;
import com.hexaware.simplyfly.exception.InvalidSeatException;
import com.hexaware.simplyfly.exception.SeatNotVacantException;
import com.hexaware.simplyfly.service.IBookingService;
import com.hexaware.simplyfly.service.ICustomerService;
import com.hexaware.simplyfly.service.JwtService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	
	Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IBookingService bookingService;
	
	@Autowired
	JwtService jwtService;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/createAccount")
	public Customer createAccount(@RequestBody Customer customer) throws Exception {
		return customerService.createAccount(customer);
	}
	
	@PutMapping("/updateAccount")
	@PreAuthorize("hasAuthority('Customer')")
	public Customer updateAccount(@RequestBody Customer customer) {
		return customerService.editAccountInfo(customer);
	}
	
	@DeleteMapping("/deleteAccount/{username}")
	@PreAuthorize("hasAuthority('Customer')")
	public String deleteAccount(@PathVariable String username) {
		return customerService.deleteAccount(username);
	}	
	
	@PostMapping("/booking/book-flight/{username}")
	@PreAuthorize("hasAuthority('Customer')")
	public Bookings bookFlight(@RequestBody BookingDTO bookingDTO, @PathVariable String username) throws CustomerNotFoundException, SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException {
		return bookingService.bookFlight(bookingDTO, username);
	}
	
	@GetMapping("booking/get-by-customer/{username}")
	@PreAuthorize("hasAuthority('Customer')")
	public List<Bookings> getBookingByUsername(@PathVariable String username){
		return  bookingService.getAllBookingsByUsername(username);
	}
	
	@PutMapping("/booking/cancel-flight/{username}/{bookingId}")
	@PreAuthorize("hasAuthority('Customer')")
	public String cancelFlight(@PathVariable String username, @PathVariable Integer bookingId) throws BookingNotFoundException {
		return bookingService.cancelBooking(bookingId, username);
	}
	
	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		}
		else {
			throw new UsernameNotFoundException("invalid user request");
		}
	
	}
	
	@GetMapping("/enter")
	public String giveGreeting() {
		return "hello";
	}
	
	
	
	
}
