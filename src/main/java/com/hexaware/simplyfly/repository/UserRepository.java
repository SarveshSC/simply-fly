package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.simplyfly.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public int removeUserByUsername(String username);
	public User findUserByUsername(String username);
}
