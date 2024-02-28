package com.hexaware.simplyfly.restcontroller;

import java.util.List;

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

import com.hexaware.simplyfly.dto.AdminDTO;
import com.hexaware.simplyfly.dto.AirlineDTO;
import com.hexaware.simplyfly.dto.AirportDTO;
import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.service.IAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/simply-fly/admin")
public class AdminRestController {
	
	@Autowired
	IAdminService service;
	
	@PostMapping("/add-airport")
	@PreAuthorize("hasAuthority('Admin')")
	public Airports addAirport(@RequestBody AirportDTO airportDTO) {
		return service.addAirport(airportDTO);
	}
	
	@PutMapping("/modify-aiport")
	@PreAuthorize("hasAuthority('Admin')")
	public Airports updateAirport(@RequestBody AirportDTO airportDTO) throws AirportNotFoundException {
		return service.updateAirport(airportDTO);
	}
	
	@DeleteMapping("/remove-airport/{airportCode}")
	@PreAuthorize("hasAuthority('Admin')")
	public String removeAirport(@PathVariable String airportCode) throws AirportNotFoundException {
		return service.removeAirport(airportCode);
	}
	
	@GetMapping("/find-airport/{airportCode}")
	@PreAuthorize("hasAuthority('Admin')")
	public AirportDTO findAirport(@PathVariable String airportCode) throws AirportNotFoundException {
		return service.findAirport(airportCode);
	}
	
	//had a doubt 
	@GetMapping("/list-all-airports")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Airports> getAllAirports(){
		return service.getAllAirports();
	}
	
	@PostMapping("/add-user")
	public User addUser(@RequestBody UserDTO userDTO) throws Exception {
		return service.addUser(userDTO);
	}
	
	@PostMapping("/add-admin")
	//@PreAuthorize("hasAuthority('Admin')")
	public Admin addAdmin(@RequestBody AdminDTO adminDTO) throws Exception {
		return service.addAdmin(adminDTO);
	}
	
	@PostMapping("/modify-user")
	@PreAuthorize("hasAuthority('FlightOwner','Admin')")
	public User modifyUser(@RequestBody UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException {
		return service.modifyUser(userDTO);
	}
	
	@GetMapping("/find-by-username/{username}")
	@PreAuthorize("hasAuthority('Admin')")
	public User findUserByUsername(@PathVariable String username) throws UserNotFoundException {
		return service.findUserByUsername(username);
	}
	
	@GetMapping("/remove-by-username/{username}")
	@PreAuthorize("hasAuthority('Admin')")
	public String removeUserByUsername(@PathVariable String username) throws UserNotFoundException {
		return service.removeUserByUsername(username);
	}
	
	@GetMapping("/list-all-users")
	@PreAuthorize("hasAuthority('Admin')")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/list-all-customers")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Customer> getAllCustomers(){
		return service.getAllCustomers();
	}
	
	@PostMapping("/add-airline")
	@PreAuthorize("hasAuthority('Admin')")
	public Airlines addAirline(@Valid @RequestBody AirlineDTO airlineDTO) {
		return service.addAirline(airlineDTO);
	}
	
	@PutMapping("/update-airline")
	@PreAuthorize("hasAuthority('Admin')")
	public Airlines updateAirline(@RequestBody AirlineDTO airlineDTO) throws AirlineNotFoundException {
		return service.modifyAirline(airlineDTO);
	}
	
	@GetMapping("/list-all-airlines")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Airlines> getAllAirlines(){
		return service.getAllAirlines();
	}
	
	@PutMapping("/approve-user/{username}")
	@PreAuthorize("hasAuthority('Admin')")
	public String approveUser( @PathVariable String username) throws UserNotFoundException {
		return service.approveUser(username);
	}
	
	@PutMapping("/reject-user/username")
	@PreAuthorize("hasAuthority('Admin')")
	public String rejectUser(@PathVariable String username) throws UserNotFoundException{
		return service.rejectUser(username);
	}
}
