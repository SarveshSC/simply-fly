package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.SeatStructure;

@Repository
public interface SeatStructureRepository extends JpaRepository<SeatStructure, String>{
	
	@Query(value = "select seat_no from seat_structure where seat_no not in (select seat_no from seats where status = 'Booked' and flight_trip_id = ?1);", nativeQuery = true)
	public List<SeatStructure> getVacantSeats(Integer flightTripId);
}
