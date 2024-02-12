package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer>{

}
