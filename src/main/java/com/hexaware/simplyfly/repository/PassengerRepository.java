package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.simplyfly.entities.Passengers;

public interface PassengerRepository extends JpaRepository<Passengers, Integer>{

}
