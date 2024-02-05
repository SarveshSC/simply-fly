package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.entities.Gender;

public class PassengerDTO {
	private Integer passengerId;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	
	public PassengerDTO() {
		super();
	}

	public PassengerDTO(Integer passengerId, String firstName, String lastName, Integer age, Gender gender) {
		super();
		this.passengerId = passengerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
