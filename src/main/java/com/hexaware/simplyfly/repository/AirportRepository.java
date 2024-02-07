package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airports;

@Repository
public interface AirportRepository extends JpaRepository<Airports, String>{

	public Airports findByLocation(String Location);
	
}
