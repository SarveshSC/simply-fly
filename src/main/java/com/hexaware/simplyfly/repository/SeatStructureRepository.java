package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.SeatStructure;

@Repository
public interface SeatStructureRepository extends JpaRepository<SeatStructure, String>{

}
