package com.hexaware.simplyfly.dto;

public class CustomerDTO {
	private String username;
	private String name;
	private String email;
	private String password;
	private String contact;
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
