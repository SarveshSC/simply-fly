package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.Passengers;

@Repository
public interface PassengerRepository extends JpaRepository<Passengers, String>{
	
	@Query(value=" select passenger_id from passengers  order by passenger_id  desc limit 1;",nativeQuery = true)
	public String getMaxPassengerId();

}
