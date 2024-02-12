package com.hexaware.simplyfly.dto;

import org.hibernate.validator.constraints.UniqueElements;

import com.hexaware.simplyfly.entities.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
	
	
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Username must contain atleast one letter and one number")
	private String username;
	
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password must contain 1 upper case, 1 lower case and atleast 8 characters") 
	// at least one digit, one lower case, one upper case, length > 7
	private String password;
	
	private Roles role;
	
	@Email(message = "Enter a valid email")
	@UniqueElements(message = "Email already registered")
	private String email;
	
	@NotBlank
	private String airlineId;
	
	
	public UserDTO() {
		super();
	}


	public UserDTO( String username, String password, Roles role, String email, String airline) {
		super();
		
		this.username = username;
		this.password = password;
		this.role = role;
		this.email = email;
		this.airlineId = airline;
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
