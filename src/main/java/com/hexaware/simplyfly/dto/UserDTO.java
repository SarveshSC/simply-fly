package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.entities.Roles;

public class UserDTO {
	private int userId;
	private String username;
	private String password;
	private Roles role;
	private String email;
	private String airlineId;
	
	
	public UserDTO() {
		super();
	}


	public UserDTO(int userId, String username, String password, Roles role, String email, String airline) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.airlineId = airline;
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


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAirlineId() {
		return airlineId;
	}


	public void setAirlineId(String airline) {
		this.airlineId = airline;
	}
	
}
