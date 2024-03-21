package com.hexaware.simplyfly.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.dto.BookingDTO;
import com.hexaware.simplyfly.dto.PassengerDTO;
import com.hexaware.simplyfly.dto.UpdateProfileDTO;
import com.hexaware.simplyfly.entities.Admin;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.repository.AdminRepository;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;
import com.hexaware.simplyfly.repository.SeatStructureRepository;
import com.hexaware.simplyfly.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	SeatStructureRepository seatStructureRepo;

	@Autowired
	FlightTripRepository flightTripRepo;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AdminRepository adminRepository;

	@Override
	public Customer createAccount(Customer customer) throws Exception {
		logger.info("trying to create account");
		if (!userRepository.existsById(customer.getUsername()) && !adminRepository.existsById(customer.getUsername())) {
			if (!custRepo.existsById(customer.getUsername())) {
				customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				return custRepo.save(customer);
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
	public Customer editAccountInfo(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public String deleteAccount(String username) {
		logger.info("Trying to delete account for username " + username);
		custRepo.deleteById(username);

		return "Account Deleted";
	}

	@Override
	public List<SeatStructure> getVacantSeats(Integer flightTripId) throws InvalidFlightException {
		FlightTrip flightTrip = flightTripRepo.findById(flightTripId).orElse(null);
		if (flightTrip == null) {
			throw new InvalidFlightException("Invalid Flight Trip Id");
		}

		return seatStructureRepo.getVacantSeats(flightTripId);
	}

	@Override
	public List<BookingDTO> getBookingsByCustomerUsername(String username) {
		logger.info(username);
		logger.info("the method is called in the service");
		return bookingRepo.getBookingsByCustomerUsername(username).stream().map(booking -> new BookingDTO(
				booking.getBookingId(), booking.getAmount(), booking.getBookingDateTime(), booking.getStatus(),
				booking.getPassengers().stream()
						.map(passenger -> new PassengerDTO(passenger.getPassengerId(), passenger.getName(),
								passenger.getAge(), passenger.getGender(),
								Optional.ofNullable(passenger.getSeat()).map(seat -> seat.getSeatNo().getSeatNo())
										.orElse("no seat")))
						.collect(Collectors.toSet()),
				booking.getCustomer().getUsername(), booking.getFlightTripForBooking().getFlightTripId()))
				.collect(Collectors.toList());
	}

	@Override
	public String updateProfile(UpdateProfileDTO updateProfileDTO, String username) throws Exception {
		Boolean exists = false;
		Customer customer = custRepo.findById(username).orElse(null);

		if (customer != null) {
			if (customer.getContact().equals(updateProfileDTO.getContact())) {
				exists = true;
			} else {
				exists = false;
			}
			if (custRepo.existsByContact(updateProfileDTO.getContact()) > 0 && !exists) {
				throw new Exception("Contact already exists");
			}

			if (customer.getEmail().equals(updateProfileDTO.getEmail())) {
				exists = true;
			} else {
				exists = false;
			}
			if (customer.getEmail().equals(updateProfileDTO.getEmail()) && !exists) {
				if (custRepo.existsByEmail(updateProfileDTO.getEmail()) > 0) {
					throw new Exception("Email already exists");
				}
			}

			else {
				customer.setEmail(updateProfileDTO.getEmail());
				customer.setName(updateProfileDTO.getName());
				customer.setContact(updateProfileDTO.getContact());

				custRepo.save(customer);

				return "Profile Updated";
			}
		}

		User user = userRepository.findById(username).orElse(null);
		if (user != null) {
			if (user.getEmail().equals(updateProfileDTO.getEmail())) {
				exists = true;
			} else {
				exists = false;
			}

			if (userRepository.existsByEmail(updateProfileDTO.getEmail()) > 0 && !exists) {
				throw new Exception("email already exists");
			} else {
				user.setEmail(updateProfileDTO.getEmail());
				userRepository.save(user);
				return "Profile Updated";
			}
		}

		Admin admin = adminRepository.findById(username).orElse(null);
		if (admin != null) {

			if (admin.getEmail().equals(updateProfileDTO.getEmail())) {
				exists = true;
			} else {
				exists = false;
			}

			if (adminRepository.existsByEmail(updateProfileDTO.getEmail()) > 0 && !exists) {
				logger.warn("Email already exists");
				throw new Exception("Email already exists");
			} else {
				admin.setEmail(updateProfileDTO.getEmail());
				adminRepository.save(admin);
				return "Profile updated";
			}
		}

		throw new UsernameNotFoundException("Username not found with " + username);

	}

	@Override
	public UpdateProfileDTO getProfille(String username) {
		Customer customer = custRepo.findById(username).orElse(null);
		UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO();
		if (customer != null) {
			updateProfileDTO.setContact(customer.getContact());
			updateProfileDTO.setEmail(customer.getEmail());
			updateProfileDTO.setName(customer.getName());
			return updateProfileDTO;
		}

		User user = userRepository.findById(username).orElse(null);
		if (user != null) {
			updateProfileDTO.setEmail(user.getEmail());
			return updateProfileDTO;
		}

		Admin admin = adminRepository.findById(username).orElse(null);
		if (admin != null) {
			updateProfileDTO.setEmail(admin.getEmail());
			return updateProfileDTO;
		}

		throw new UsernameNotFoundException("Username not found with " + username);
	}
}
