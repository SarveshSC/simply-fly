package com.hexaware.simplyfly.entities;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@Email(message = "Invalid Email Format")
	@Column(unique = true)
	private String email;
	
	@OneToOne()
	@JoinColumn(name="airlineId")
	private Airlines airlineFromUser;
	
	

	public User() {
		super();
	}

	public User( String username, String password, Roles role, String email, Airlines airlineFromUser) {
		super();
		
		this.username = username;
		this.password = password;
		this.role = role;
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

	public Airlines getAirline() {
		return airlineFromUser;
	}

	public void setAirline(Airlines airline) {
		this.airlineFromUser = airline;
	}
	
	
}
