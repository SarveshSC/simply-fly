package com.hexaware.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.simplyfly.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{
	
	@Query(value="select count(*) from admin where email=?1",nativeQuery = true)
	public Integer existsByEmail(String email);

}
