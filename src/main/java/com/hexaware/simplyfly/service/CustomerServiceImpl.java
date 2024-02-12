package com.hexaware.simplyfly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.exception.InvalidFlightException;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;
import com.hexaware.simplyfly.repository.SeatStructureRepository;
import com.hexaware.simplyfly.repository.UserRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	
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


	@Override
	public Customer createAccount(Customer customer) throws Exception {
		if(!userRepository.existsById(customer.getUsername()))
			return custRepo.save(customer);
		else
			throw new Exception("username already exists");
	}

	@Override
	public Customer editAccountInfo(Customer customer) {
		return custRepo.save(customer);
	}

	@Override
	public String deleteAccount(String username) {
		custRepo.deleteById(username);
		
		return "Account Deleted";
	}

	@Override
	public List<SeatStructure> getVacantSeats(Integer flightTripId) throws InvalidFlightException {
		FlightTrip flightTrip = flightTripRepo.findById(flightTripId).orElse(null);
		if(flightTrip == null) {
			throw new InvalidFlightException("Invalid Flight Trip Id");
		}
		
		return seatStructureRepo.getVacantSeats(flightTripId);
	}
	
	

}
