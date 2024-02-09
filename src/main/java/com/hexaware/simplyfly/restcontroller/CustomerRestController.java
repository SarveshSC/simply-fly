package com.hexaware.simplyfly.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.simplyfly.entities.Customer;
import com.hexaware.simplyfly.service.ICustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	
	Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	ICustomerService service;
	
	@PostMapping("/createAccount")
	public Customer createAccount(@RequestBody Customer customer) {
		return service.createAccount(customer);
	}
	
	@PutMapping("/updateAccount")
	public Customer updateAccount(@RequestBody Customer customer) {
		return service.editAccountInfo(customer);
	}
	
	@DeleteMapping("/deleteAccount/{username}")
	public String deleteAccount(@PathVariable String username) {
		return service.deleteAccount(username);
	}	
	
	
}
