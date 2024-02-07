package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.entities.Flights;

@Repository
public interface FlightRepository extends JpaRepository<Flights,String> {
	List<Flights> findBySource(Airports sourceIata);

	List<Flights> findByDestination(Airports destinationIata);
}
