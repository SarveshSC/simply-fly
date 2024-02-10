package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hexaware.simplyfly.repository.AirlineRepository;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;
import com.hexaware.simplyfly.repository.UserRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	AirportRepository airportRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	AirlineRepository airlineRepo;

	@Override
	public Airports addAirport(AirportDTO airportDTO) {
		Airports airport = new Airports();
		airport.setName(airportDTO.getName());
		airport.setIataCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());
		return airportRepo.save(airport);
	}

	@Override
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportDTO.getIataCode()).orElseThrow(
				() -> new AirportNotFoundException("Airport with code " + airportDTO.getIataCode() + " not found."));
		airport.setName(airportDTO.getName());
		airport.setIataCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());

		return airportRepo.save(airport);
	}

	@Override
	public String removeAirport(String airportCode) throws AirportNotFoundException {
		if (findAirport(airportCode) == null) {
			throw new AirportNotFoundException("Airport with code " + airportCode + " not found.");
		}
		airportRepo.deleteById(airportCode);
		return "Airport with code " + airportCode + " deleted successfully";

	}

	@Override
	public AirportDTO findAirport(String airportCode) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportCode).orElse(null);
		if (airport == null) {
			throw new AirportNotFoundException("Airport with code " + airportCode + " not found.");
		}

		AirportDTO airportDTO = new AirportDTO();
		airportDTO.setName(airport.getName());
		airportDTO.setLocation(airport.getLocation());
		airportDTO.setIataCode(airport.getIataCode());
		return airportDTO;
	}

	@Override
	public List<Airports> getAllAirports() {
		return airportRepo.findAll();
	}

	@Override
	public User addUser(UserDTO userDTO) throws AirlineNotFoundException {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

		Airlines airline = airlineRepo.findById(userDTO.getAirlineId()).orElseThrow(
				() -> new AirlineNotFoundException("Airline with id " + userDTO.getAirlineId() + " not found."));
		user.setAirline(airline);
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());

		return userRepo.save(user);
	}

	@Override
	public User modifyUser(UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException {
		User user = userRepo.findById(userDTO.getUserId()).orElse(null);

		if (user == null) {
			throw new UserNotFoundException("User Credentials Invalid");
		}
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

		Airlines airline = airlineRepo.findById(userDTO.getAirlineId()).orElseThrow(
				() -> new AirlineNotFoundException("Airline with id " + userDTO.getAirlineId() + " not found."));
		user.setAirline(airline);
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());

		return userRepo.save(user);
	}

	@Override
	public String removeUserByUsername(String username) throws UserNotFoundException {
		User user = userRepo.findUserByUsername(username);

		if (user == null) {
			throw new UserNotFoundException("User Credentials Invalid");
		}
		userRepo.removeUserByUsername(username);
		return "User with username " + username + " deleted";
	}

	@Override
	public User findUserByUsername(String username) throws UserNotFoundException {
		User user = userRepo.findUserByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("User Credentials Invalid");
		}
		return userRepo.findUserByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Airlines addAirline(AirlineDTO airlineDTO) {
		Airlines airline = new Airlines();
		airline.setAirlineId(airlineDTO.getAirlineId());
		airline.setAirlineName(airlineDTO.getAirlineName());

		return airlineRepo.save(airline);
	}

	@Override
	public Airlines modifyAirline(AirlineDTO airlineDTO) throws AirlineNotFoundException {
		Airlines airline = airlineRepo.findById(airlineDTO.getAirlineId()).orElse(null);
		if (airline == null)
			throw new AirlineNotFoundException("Airline with id " + airlineDTO.getAirlineId() + " not found");
		airline.setAirlineId(airlineDTO.getAirlineId());
		airline.setAirlineName(airlineDTO.getAirlineName());

		return airlineRepo.save(airline);
	}

	@Override
	public List<Airlines> getAllAirlines() {
		return airlineRepo.findAll();
	}

}
