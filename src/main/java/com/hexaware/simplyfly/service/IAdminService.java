package com.hexaware.simplyfly.service;

import java.util.List;

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

public interface IAdminService {
	public Airports addAirport(AirportDTO airportDTO);
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException;
	public String removeAirport(String airportCode) throws AirportNotFoundException;
	public AirportDTO findAirport(String airportCode) throws AirportNotFoundException;
	public List<Airports> getAllAirports();
	
	public User addUser(UserDTO userDTO) throws AirlineNotFoundException, Exception;
	public User modifyUser(UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException;
	public User findUserByUsername(String username) throws UserNotFoundException;
	public String removeUserByUsername(String username) throws UserNotFoundException;
	public List<User> getAllUsers();
	public List<UserDTO> getUserRequests();
	public String approveUser(String username) throws UserNotFoundException;
	public String rejectUser(String username) throws UserNotFoundException;
	
	public Airlines addAirline(AirlineDTO airlineDTO);
	public Airlines modifyAirline(AirlineDTO airlineDTO) throws AirlineNotFoundException;
	public List<Airlines> getAllAirlines();
	public Admin addAdmin(AdminDTO adminDTO) throws Exception;
	
	public List<Customer> getAllCustomers();
}
