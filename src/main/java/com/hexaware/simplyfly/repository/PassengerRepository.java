package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Passengers;

@Repository
public interface PassengerRepository extends JpaRepository<Passengers, String>{

}
