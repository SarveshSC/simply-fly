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
}
