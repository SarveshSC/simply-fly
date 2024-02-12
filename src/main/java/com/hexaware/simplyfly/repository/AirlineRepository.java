package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Airlines;

@Repository
public interface AirlineRepository extends JpaRepository<Airlines, String> {
	
	@Modifying
	@Query(value="insert into Airlines(airlineId, airlineName) values (:airlineId, :airlineName)")
	public void create(String airlineId, String airlineName);
}
