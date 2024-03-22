package com.hexaware.simplyfly.restcontroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.hexaware.simplyfly.dto.UpdateProfileDTO;
import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.exception.BookingNotFoundException;
import com.hexaware.simplyfly.exception.CustomerNotFoundException;
import com.hexaware.simplyfly.exception.FlightNotFoundException;
import com.hexaware.simplyfly.exception.InsufficientPassengersException;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.exception.InvalidSeatException;
import com.hexaware.simplyfly.exception.SeatNotVacantException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.service.IBookingService;
import com.hexaware.simplyfly.service.ICustomerService;
import com.hexaware.simplyfly.service.IMailService;
import com.hexaware.simplyfly.service.IPdfGenerator;
import com.hexaware.simplyfly.service.JwtService;
import com.hexaware.simplyfly.service.pdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/simply-fly/customers")
public class CustomerRestController {
	
	Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	ICustomerService customerService;
	
	@Autowired
	IBookingService bookingService;
  
  	@Autowired
	JwtService jwtService;
  	
  	@Autowired
  	IPdfGenerator generator;
  	
  	@Autowired
	IMailService mailService;
	
	@Autowired
	private AuthenticationManager authManager;
	

	@PostMapping("/create-account")
	public Customer createAccount(@RequestBody Customer customer) throws Exception {
		return customerService.createAccount(customer);
	}

	@PutMapping("/update-account")
	@PreAuthorize("hasAuthority('Customer')")
	public Customer updateAccount(@RequestBody Customer customer) {
		return customerService.editAccountInfo(customer);
	}
	

	@DeleteMapping("/delete-account/{username}")
	@PreAuthorize("hasAuthority('Customer')")
	public String deleteAccount(@PathVariable String username) {
		return customerService.deleteAccount(username);
	}	
	
	@PostMapping("/booking/book-flight/{username}/{flightTripId}")
	@PreAuthorize("hasAuthority('Customer')")
	public Bookings bookFlight(@RequestBody BookingDTO bookingDTO, @PathVariable String username, @PathVariable Integer flightTripId) throws CustomerNotFoundException, SeatNotVacantException, FlightNotFoundException, InvalidSeatException, InsufficientPassengersException {
		bookingDTO.setFlightTripId(flightTripId);
		return bookingService.bookFlight(bookingDTO, username);
	}
	
	
	
	@GetMapping("/booking/get-by-customer/{username}")
	@PreAuthorize("hasAuthority('Customer')")
	public List<Bookings> getBookingByUsername(@PathVariable String username){
		return  bookingService.getAllBookingsByUsername(username);
	}
	
	@PutMapping("/booking/cancel-flight/{username}/{bookingId}")
	@PreAuthorize("hasAuthority('Customer')")
	public String cancelFlight(@PathVariable String username, @PathVariable Integer bookingId) throws BookingNotFoundException {
		return bookingService.cancelBooking(bookingId, username);
	}
	


	@GetMapping("/booking/get-all-vacant-seats/{flightTripId}")
	@PreAuthorize("hasAuthority('Customer')")
	public List<SeatStructure> getAllVacantSeats(@PathVariable Integer flightTripId) throws InvalidFlightException{
		return customerService.getVacantSeats(flightTripId);
	}

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return jwtService.generateToken(authRequest.getUsername(),userDetails.getAuthorities());
		}
		else {
			throw new UsernameNotFoundException(authRequest.getUsername());
		}
	
	}
	
	@PutMapping("/update-password")
	@PreAuthorize("hasAnyAuthority('Customer','Admin','FlightOwner')")
	public String updatePassword(@RequestBody AuthRequest authRequest) throws Exception {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return customerService.updatePassword(authRequest);
		}
		else {
			throw new Exception("the password is incorrect");
		}
	
	}
	
	@GetMapping("/enter")
	public String giveGreeting() {
		return "hello";
	}
	
	@GetMapping("/booking/get-all-bookings")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Bookings> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	
	
	@GetMapping("/get-customer-bookings-by-username/{username}")
	@PreAuthorize("hasAnyAuthority('Customer','Admin')")
	public List<BookingDTO> getBookingsByCustomerUsername(@PathVariable String username) throws UserNotFoundException{
		logger.info("the booking method is called in the controller");
		return customerService.getBookingsByCustomerUsername(username);
	}

	@GetMapping("/getPdf/{bookingId}")
	@PreAuthorize("hasAnyAuthority('Customer','Admin')")
	public void generatePdf(HttpServletResponse response,@PathVariable int bookingId) throws Exception {
		response.setContentType("application/pdf");
		DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime=dateFormatter.format(new Date());
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=pdf_"+currentDateTime+".pdf";
		response.setHeader(headerKey, headerValue);
		generator.export(response, bookingId);
	}
	
	@PutMapping("/update-profile/{username}")
	@PreAuthorize("hasAnyAuthority('Customer','Admin','FlightOwner')")
	public String updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO,@PathVariable String username) throws Exception {
	return customerService.updateProfile(updateProfileDTO, username);
	}
	
	@GetMapping("/get-profile/{username}")
	@PreAuthorize("hasAnyAuthority('Customer','Admin','FlightOwner')")
	public UpdateProfileDTO getProfille(@PathVariable String username) {
	return customerService.getProfille(username);
	}
	
	@GetMapping("/mail-ticket/{bookingId}")
	@PreAuthorize("hasAuthority('Customer')")
	public void sendMail(HttpServletResponse response, @PathVariable int bookingId) throws Exception {
		response.setContentType("application/pdf");
		DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime=dateFormatter.format(new Date());
		
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=pdf_"+currentDateTime+".pdf";
		response.setHeader(headerKey, headerValue);
		mailService.sendEmail(response, bookingId);
	}
		
	
	
	
	
}

