package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {
	@Id
	private String username;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	
	@Column(nullable = false)
	@Min(value = 8)
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$") 
	// at least one digit, one lower case, one upper case, length > 7
	private String password;
	
	@Column(nullable = false, unique = true)
	private String contact;
	
	@Min(value = 18)
	private int age;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<Bookings> bookings = new HashSet<>();
	
	public Customer() {
		super();
	}

	public Customer(String username, String name, String email, String password, String contact, int age,
			Set<Bookings> bookings) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.age = age;
		this.bookings = bookings;
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

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}
	
	

}