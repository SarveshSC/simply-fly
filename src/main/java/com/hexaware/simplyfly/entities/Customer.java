package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private String username;
	private String name;
	private String email;
	private String password;
	private String contact;
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private Set<Bookings> bookings = new HashSet<Bookings>();

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

	public Customer() {
		super();
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


