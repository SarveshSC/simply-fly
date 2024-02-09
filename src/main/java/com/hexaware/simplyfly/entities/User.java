package com.hexaware.simplyfly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String username;
	private String password;
	private String role;
	private String email;
	
	@OneToOne()
	@JoinColumn(name="airlineId")
	private Airlines airlineFromUser;
	
	

	public User() {
		super();
	}

	public User(int userId, String username, String password, String role, String email, Airlines airline) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.airlineFromUser = airline;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Airlines getAirline() {
		return airlineFromUser;
	}

	public void setAirline(Airlines airline) {
		this.airlineFromUser = airline;
	}
	
	
}
