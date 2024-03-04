package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, String>{
	
	@Query(value="select payment_id from payments where booking_id = ?1",nativeQuery = true)
	public String getPaymentId(Integer bookingId);

}
