package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.AdminDTO;
import com.hexaware.simplyfly.dto.AirlineDTO;
import com.hexaware.simplyfly.dto.AirportDTO;
import com.hexaware.simplyfly.dto.UserDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Airlines;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.entities.UserStatus;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.AdminRepository;
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

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AdminRepository adminRepository;

	Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public Airports addAirport(AirportDTO airportDTO) throws Exception {
		if (airportRepo.existsById(airportDTO.getIataCode())) {
			logger.warn("Airport with the IATA Code " + airportDTO.getIataCode() + " already exists");
			throw new Exception("Airport with the IATA Code " + airportDTO.getIataCode() + " already exists");
		}
		Airports airport = new Airports(airportDTO.getIataCode(), airportDTO.getName(), airportDTO.getLocation());
		
		logger.info("Airport " + airportDTO.getName() + " added.");

		return airportRepo.save(airport);
	}

	@Override
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportDTO.getIataCode())
				.orElseThrow(() -> new AirportNotFoundException(airportDTO.getIataCode()));
		airport.setName(airportDTO.getName());
		airport.setIataCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());
		
		logger.info("Airport " + airportDTO.getIataCode() + " updation.");

		return airportRepo.save(airport);
	}

	@Override
	public String removeAirport(String airportCode) throws AirportNotFoundException {
		if (findAirport(airportCode) == null) {
			throw new AirportNotFoundException(airportCode);
		}
		airportRepo.deleteById(airportCode);
		logger.info("Airport " + airportCode + " deletion.");
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
	public User addUser(UserDTO userDTO) throws Exception {
		User user = new User();
		if (!customerRepo.existsById(userDTO.getUsername()) && !adminRepository.existsById(userDTO.getUsername())) {
			if (!userRepo.existsById(userDTO.getUsername())
					&& userRepo.findByAirlineId(userDTO.getAirlineId()) == null) {
				user.setUsername(userDTO.getUsername());
				user.setEmail(userDTO.getEmail());

				Airlines airline = airlineRepo.findById(userDTO.getAirlineId())
						.orElseThrow(() -> new AirlineNotFoundException(userDTO.getAirlineId()));
				user.setAirline(airline);
				user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				user.setUserStatus(UserStatus.PENDING);

				return userRepo.save(user);
			} else {
				logger.warn("User already exists");
				throw new Exception("User already exists");
			}
		} else {
			logger.warn("Username already exists");
			throw new Exception("Username already exists");
		}

	}

	@Override
	public User modifyUser(UserDTO userDTO) throws UserNotFoundException, AirlineNotFoundException {
		User user = userRepo.findById(userDTO.getUsername()).orElse(null);

		if (user == null) {
			throw new UserNotFoundException(userDTO.getUsername());
		}
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());

		Airlines airline = airlineRepo.findById(userDTO.getAirlineId())
				.orElseThrow(() -> new AirlineNotFoundException(userDTO.getAirlineId()));
		user.setAirline(airline);
		user.setPassword(userDTO.getPassword());
		
		logger.info("User " + userDTO.getUsername() + " profile updation in process.");
		
		return userRepo.save(user);
	}

	@Override
	public String removeUserByUsername(String username) throws UserNotFoundException {
		User user = userRepo.findUserByUsername(username);

		if (user == null) {
			throw new UserNotFoundException(username);
		}
		userRepo.removeUserByUsername(username);
		logger.info("User with username " + username + " deleted");
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
	public Airlines addAirline(AirlineDTO airlineDTO) throws Exception {
		if (airlineRepo.existsById(airlineDTO.getAirlineId())) {
			throw new Exception("Airline already exists");
		}
		airlineRepo.create(airlineDTO.getAirlineId(), airlineDTO.getAirlineName());
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
		
		logger.info("Airline " + airlineDTO.getAirlineId() + " modification underway.");

		return airlineRepo.save(airline);
	}

	@Override
	public List<Airlines> getAllAirlines() {
		return airlineRepo.findAll();
	}

	@Override
	public Admin addAdmin(AdminDTO adminDTO) throws Exception {
		Admin admin = new Admin();
		if (!customerRepo.existsById(adminDTO.getUsername()) && !userRepo.existsById(adminDTO.getUsername())) {
			if (!adminRepository.existsById(adminDTO.getUsername())) {
				admin.setUsername(adminDTO.getUsername());
				admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
				admin.setEmail(adminDTO.getEmail());
				return adminRepository.save(admin);
			} else {
				logger.warn("User already exists");
				throw new Exception("user already exists");

			}

		} else {
			logger.warn("Username alreay exists");
			throw new Exception("username already exists");
		}
	}

	@Override
	public List<UserDTO> getUserRequests() {
		List<User> userRequests = userRepo.findAllByUserStatus(UserStatus.PENDING);
		List<UserDTO> userdto = userRequests.stream().map(user -> new UserDTO(user.getUsername(), user.getPassword(),
				user.getEmail(), user.getAirline().getAirlineId(), user.getUserStatus())).collect(Collectors.toList());

		return userdto; // Explicitly return the List<UserDTO>
	}

	@Override
	public String approveUser(String username) throws UserNotFoundException {
		User user = userRepo.findById(username).orElseThrow(() -> new UserNotFoundException(username));
		user.setUserStatus(UserStatus.APPROVED);
		user.setRole("FlightOwner");
		userRepo.save(user);
		logger.info("Flightowner " + username + " status approved.");
		return "user approved";
	}

	@Override
	public String rejectUser(String username) throws UserNotFoundException {
		User user = userRepo.findById(username).orElseThrow(() -> new UserNotFoundException(username));
		user.setUserStatus(UserStatus.REJECTED);
		user.setRole("Rejected User");
		userRepo.save(user);
		logger.info("Flightowner " + username + " status rejected.");
		return "user rejected";

	}

	@Override
	public String inactiveUser(String username) throws UserNotFoundException {
		User user = userRepo.findById(username).orElseThrow(() -> new UserNotFoundException(username));
		user.setUserStatus(UserStatus.INACTIVE);
		user.setRole("Inactive User");
		userRepo.save(user);
		logger.info("Flightowner " + username + " status inactive.");
		return "user made inactive";
	}

}
