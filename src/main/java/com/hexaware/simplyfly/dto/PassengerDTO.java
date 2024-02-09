package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.entities.Seats;

public class PassengerDTO {
	private Integer passengerId;
	private String name;
	private Integer age;
	private String gender;
	private Seats seat;
	
	public PassengerDTO() {
		super();
	}

	public PassengerDTO(Integer passengerId, String name, Integer age, String gender, Seats seat) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seat = seat;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}
	
	
}
