package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightDetails;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Integer> {

}
