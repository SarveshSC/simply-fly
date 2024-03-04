package com.hexaware.simplyfly.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
	

	
	
	@Id
	@NotNull
	@Column(unique = true)
	private String username;
	
	//@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$") 
	@NotNull
	// at least one digit, one lower case, one upper case, length > 7
	private String password;
	
	
	private  String role;
	
	@Email(message = "Invalid Email Format")
	@Column(unique = true)
	private String email;
	
	@OneToOne()
	@JoinColumn(name="airlineId")
	private Airlines airlineFromUser;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus userStatus;

	public User() {
		super();
	}

	public User( String username, String password,  String email, Airlines airlineFromUser) {
		super();
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.airlineFromUser = airlineFromUser;
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



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Airlines getAirlineFromUser() {
		return airlineFromUser;
	}

	public void setAirlineFromUser(Airlines airlineFromUser) {
		this.airlineFromUser = airlineFromUser;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
