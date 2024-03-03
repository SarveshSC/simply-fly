package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airports;

@Repository
public interface AirportRepository extends JpaRepository<Airports, String>{
	
	@Query(value="insert into Airports(iata_code) values (?1)",nativeQuery =true)
	public Airports create(String iataCode);

	public Airports findByLocation(String Location);
	
}
