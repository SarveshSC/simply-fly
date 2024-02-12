package com.hexaware.simplyfly.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.simplyfly.config.UserInfoUserDetails;
import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.repository.CustomerRepository;
import com.hexaware.simplyfly.repository.UserRepository;


@Service
public class UserInfoUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=customerRepository.findById(username).orElse(null);
		
		if(customer!=null) {
			return new UserInfoUserDetails(customer);
		}
		
		User user=userRepository.findById(username).orElse(null);
		if(user!=null) {
			return new UserInfoUserDetails(user);
		}
		throw new UsernameNotFoundException("username not found with"+username);
	}

}
