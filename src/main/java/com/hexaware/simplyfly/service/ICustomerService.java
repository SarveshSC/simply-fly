package com.hexaware.simplyfly.service;

import com.hexaware.simplyfly.entities.Customer;

public interface ICustomerService {
	public Customer createAccount(Customer customer) throws Exception;
	public Customer editAccountInfo(Customer customer);
	public String deleteAccount(String username);
	
	
}
