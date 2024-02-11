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

import jakarta.transaction.Transactional;

@Service
@Transactional
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
		Airports airport = airportRepo.create(airportDTO.getIataCode());
		airport.setName(airportDTO.getName());
		airport.setLocation(airportDTO.getLocation());

		return airportRepo.save(airport);
	}

	@Override
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportDTO.getIataCode()).orElseThrow(
				() -> new AirportNotFoundException(airportDTO.getIataCode()));
		airport.setName(airportDTO.getName());
		airport.setIataCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());

		return airportRepo.save(airport);
	}

	@Override
	public String removeAirport(String airportCode) throws AirportNotFoundException {
		if (findAirport(airportCode) == null) {
			throw new AirportNotFoundException(airportCode);
		}
		airportRepo.deleteById(airportCode);
		return "Airport with code " + airportCode + " deleted successfully";

	}

	@Override
	public AirportDTO findAirport(String airportCode) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportCode).orElse(null);
		if (airport == null) {
			throw new AirportNotFoundException(airportCode);
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
				() -> new AirlineNotFoundException(userDTO.getAirlineId()));
		user.setAirline(airline);
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());

		return userRepo.save(user);
	}

	@Override
	public User modifyUser(UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException {
		User user = userRepo.findById(userDTO.getUserId()).orElse(null);

		if (user == null) {
			throw new UserNotFoundException(userDTO.getUsername());
		}
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

		Airlines airline = airlineRepo.findById(userDTO.getAirlineId()).orElseThrow(
				() -> new AirlineNotFoundException(userDTO.getAirlineId()));
		user.setAirline(airline);
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());

		return userRepo.save(user);
	}

	@Override
	public String removeUserByUsername(String username) throws UserNotFoundException {
		User user = userRepo.findUserByUsername(username);

		if (user == null) {
			throw new UserNotFoundException(username);
		}
		userRepo.removeUserByUsername(username);
		return "User with username " + username + " deleted";
	}

	@Override
	public User findUserByUsername(String username) throws UserNotFoundException {
		User user = userRepo.findUserByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(username);
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
		airlineRepo.create(airlineDTO.getAirlineId(),airlineDTO.getAirlineName());
		Airlines airline = airlineRepo.findById(airlineDTO.getAirlineId()).orElse(null);

		return airlineRepo.save(airline);
	}

	@Override
	public Airlines modifyAirline(AirlineDTO airlineDTO) throws AirlineNotFoundException {
		Airlines airline = airlineRepo.findById(airlineDTO.getAirlineId()).orElse(null);
		if (airline == null)
			throw new AirlineNotFoundException(airlineDTO.getAirlineId());
		airline.setAirlineId(airlineDTO.getAirlineId());
		airline.setAirlineName(airlineDTO.getAirlineName());

		return airlineRepo.save(airline);
	}

	@Override
	public List<Airlines> getAllAirlines() {
		return airlineRepo.findAll();
	}

}
