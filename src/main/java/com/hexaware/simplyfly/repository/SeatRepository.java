package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.SeatStatus;
import com.hexaware.simplyfly.entities.SeatStructure;
import com.hexaware.simplyfly.entities.Seats;

@Repository
public interface SeatRepository extends JpaRepository<Seats, SeatStructure>{

	@Query(value = "select status from seats where seat_no = ?1 and flight_trip_id = ?2", nativeQuery = true)
	SeatStatus getSeatStatus(String seatNo, Integer flightTripId);
}
