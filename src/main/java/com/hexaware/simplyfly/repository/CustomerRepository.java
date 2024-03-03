package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Bookings;
import com.hexaware.simplyfly.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	

}
