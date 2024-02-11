package com.hexaware.simplyfly.dto;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
	
	@NotBlank
	@UniqueElements
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Username must contain atleast one letter and one number")
	private String username;
	
	@NotBlank
	private String name;
	
	@Email
	@UniqueElements(message = "Email already registered")
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Password must contain 1 upper case, 1 lower case and atleast 8 characters") 
	// at least one digit, one lower case, one upper case, length > 7
	private String password;
	
	@Pattern(regexp = "\\d.{10}$")
	private String contact;
	
	@Min(value = 18, message = "You should be atleast 18 year old")
	private int age;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String username, String name, String email, String password, String contact, int age) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
