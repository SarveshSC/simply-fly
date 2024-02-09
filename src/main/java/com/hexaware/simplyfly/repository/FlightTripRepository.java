package com.hexaware.simplyfly.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightTrip;
import com.hexaware.simplyfly.restcontroller.FlightDetailsController;

@Repository
public interface FlightTripRepository extends JpaRepository<FlightTrip, Integer> {
	
	

	@Query(value="select * from flight_trip where DATE(departure)=?1",nativeQuery = true)
	List<FlightTrip> findByDeparture(LocalDate departure);
	
	@Query(value = "select * from flight_trip where flight_code=?1",nativeQuery = true)
	List<FlightTrip> findByFlightCode(String flightId);


	@Query(value="select max(flight_detail_id) from flight_trip group by flight_code having flight_code=?",nativeQuery = true)
	Optional<Integer> findMaxFlightDetailId(String flightCode);


	@Query(value="select * from flight_trip where sourceiatacode=? and destinationiatacode=?",nativeQuery = true)
	List<FlightTrip> findBySourceAndDestination(String sourceIata,String destinationIata);
	
}
