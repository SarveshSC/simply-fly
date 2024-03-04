package com.hexaware.simplyfly.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Admin {

	@Id
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotNull
	private String password;

	private static final String role = "Admin";

	@Email(message = "Invalid Email Format")
	@Column(unique = true)
	private String email;

	public Admin() {
		super();
	}

	public Admin(@NotNull String username, @NotNull String password,
			@Email(message = "Invalid Email Format") String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

}
