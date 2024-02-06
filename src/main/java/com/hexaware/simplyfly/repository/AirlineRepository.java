package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airlines;

@Repository
public interface AirlineRepository extends JpaRepository<Airlines, String> {
	
}
