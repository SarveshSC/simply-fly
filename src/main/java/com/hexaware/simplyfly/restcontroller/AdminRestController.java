package com.hexaware.simplyfly.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.dto.AirlineDTO;
import com.hexaware.simplyfly.dto.AirportDTO;
import com.hexaware.simplyfly.dto.UserDTO;
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
@RequestMapping("/api/admin")
public class AdminRestController {
	
	@Autowired
	IAdminService service;
	
	@PostMapping("/add-airport")
	public Airports addAirport(@RequestBody AirportDTO airportDTO) {
		return service.addAirport(airportDTO);
	}
	
	@PutMapping("/modify-aiport")
	public Airports updateAirport(@RequestBody AirportDTO airportDTO) throws AirportNotFoundException {
		return service.updateAirport(airportDTO);
	}
	
	@DeleteMapping("/remove-airport/{airportCode}")
	public String removeAirport(@PathVariable String airportCode) throws AirportNotFoundException {
		return service.removeAirport(airportCode);
	}
	
	@GetMapping("/find-airport/{airportCode}")
	public AirportDTO findAirport(@PathVariable String airportCode) throws AirportNotFoundException {
		return service.findAirport(airportCode);
	}
	
	@GetMapping("/list-all-airports")
	public List<Airports> getAllAirports(){
		return service.getAllAirports();
	}
	
	@PostMapping("/add-user")
	public User addUser(@RequestBody UserDTO userDTO) throws AirlineNotFoundException {
		return service.addUser(userDTO);
	}
	
	@PostMapping("/modify-user")
	public User modifyUser(@RequestBody UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException {
		return service.modifyUser(userDTO);
	}
	
	@GetMapping("/find-by-username/{username}")
	public User findUserByUsername(@PathVariable String username) throws UserNotFoundException {
		return service.findUserByUsername(username);
	}
	
	@GetMapping("/remove-by-username/{username}")
	public String removeUserByUsername(@PathVariable String username) throws UserNotFoundException {
		return service.removeUserByUsername(username);
	}
	
	@GetMapping("list-all-users")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/list-all-customers")
	public List<Customer> getAllCustomers(){
		return service.getAllCustomers();
	}
	
	
	@PostMapping("/add-airline")
	public Airlines addAirline(@Valid @RequestBody AirlineDTO airlineDTO) {
		return service.addAirline(airlineDTO);
	}
	
	@PutMapping("/update-airline")
	public Airlines updateAirline(@RequestBody AirlineDTO airlineDTO) throws AirlineNotFoundException {
		return service.modifyAirline(airlineDTO);
	}
	
	@GetMapping("/list-all-airlines")
	public List<Airlines> getAllAirlines(){
		return service.getAllAirlines();
	}
}
