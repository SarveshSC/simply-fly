package com.hexaware.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.entities.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	
	@Query(value="select * from user where airline_id=?1",nativeQuery =true)
	public User findByAirlineId(String airlineId);
	public int removeUserByUsername(String username);
	public User findUserByUsername(String username);
	public User findByUsername(String username);
	public List<User> findAllByUserStatus(UserStatus userStatus);
	
}
