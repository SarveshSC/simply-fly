package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Flights;

@Repository
public interface FlightRepository extends JpaRepository<Flights,String> {
	
	@Query(value = "select * from flights where airline_id = ?1", nativeQuery = true)
	List<Flights> findByAirline(String airlineId);
	
}
