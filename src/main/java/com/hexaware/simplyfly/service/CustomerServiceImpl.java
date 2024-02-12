package com.hexaware.simplyfly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.repository.BookingRepository;
import com.hexaware.simplyfly.repository.CustomerRepository;
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

}
