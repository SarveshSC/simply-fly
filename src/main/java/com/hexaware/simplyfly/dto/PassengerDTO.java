package com.hexaware.simplyfly.dto;

import com.hexaware.simplyfly.entities.Gender;

public class PassengerDTO {
	private Integer passengerId;
	private String name;
	private Integer age;
	private Gender gender;
	private String seat;
	private Integer flightTripId;
	
	public PassengerDTO() {
		super();
	}

	public PassengerDTO(Integer passengerId, String name, Integer age, Gender gender, String seat,
			Integer flightTripId) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seat = seat;
		this.flightTripId = flightTripId;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Integer getFlightTripId() {
		return flightTripId;
	}

	public void setFlightTripId(Integer flightTripId) {
		this.flightTripId = flightTripId;
	}

	
}
