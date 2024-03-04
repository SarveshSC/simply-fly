package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Bookings;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer>{
	
	@Query(value="select * from bookings where customer_id = ?1", nativeQuery = true)
	public List<Bookings> findAllByUsername(String username);
	
	@Query(value=" select * from bookings where flight_trip_id=?",nativeQuery = true)
	List<Bookings> findBookingsByFlightTripId(Integer flightTripId);
	
	
	@Query(value="select * from bookings where flight_trip_id in (select flight_trip_id from flight_trip where flight_code in (select flight_code from flights where airline_id=(select airline_id from user where username=?1)))",nativeQuery = true)
	List<Bookings> findBookingsByUserName(String username);
	
	@Query(value = "select * from bookings where customer_id=?1",nativeQuery = true)
	public List<Bookings> getBookingsByCustomerUsername(String username);
	
	@Query(value = "select * from bookings where booking_id=?1",nativeQuery = true)
	public Bookings getBookingsByBookingId(Integer bookingId);
	
}