package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Bookings;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer>{

}
