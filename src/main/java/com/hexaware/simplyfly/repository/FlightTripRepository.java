package com.hexaware.simplyfly.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.FlightTrip;

@Repository
public interface FlightTripRepository extends JpaRepository<FlightTrip, Integer> {
	
	@Query(value="select ticket_price from flight_trip ft inner join bookings b on ft.flight_trip_id = b.flight_trip_id where b.booking_id = ?1",nativeQuery = true)
	Double findTicketPriceByBookingId(Integer bookingId);

	@Query(value="select * from flight_trip where DATE(departure)=?1",nativeQuery = true)
	List<FlightTrip> findByDeparture(LocalDate departure);
	
	@Query(value = "select * from flight_trip where flight_code=?1",nativeQuery = true)
	List<FlightTrip> findByFlightCode(String flightId);


	@Query(value="select max(flight_detail_id) from flight_trip group by flight_code having flight_code=?",nativeQuery = true)
	Optional<Integer> findMaxFlightDetailId(String flightCode);


	@Query(value="select * from flight_trip where sourceiatacode=? and destinationiatacode=?",nativeQuery = true)
	List<FlightTrip> findBySourceAndDestination(String sourceIata,String destinationIata);
	
}
