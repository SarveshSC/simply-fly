package com.hexaware.simplyfly.entities;

public class Customers {
	String customerId;
	String name;
	String email;
	String password;
	String contact;
	
	public Customers() {
		super();
	}
	
	public Customers(String customerId, String name, String email, String password, String contact) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
	}

	public String getCustomerId() {
		return customerId;
	}
	public void setUsername(String customerId) {
		this.customerId = customerId;
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
	
	
}


