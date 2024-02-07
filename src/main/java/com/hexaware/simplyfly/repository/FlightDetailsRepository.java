package com.hexaware.simplyfly.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightDetails;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Integer> {

	List<FlightDetails> findByDeparture(LocalDateTime departure);
	List<FlightDetails> findByFlightCode(String flightId);
}
