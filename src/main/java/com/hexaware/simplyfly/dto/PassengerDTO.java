package com.hexaware.simplyfly.dto;

import java.util.Optional;

import com.hexaware.simplyfly.entities.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class PassengerDTO {
	private String passengerId;

	@NotBlank
	private String name;

	@PositiveOrZero
	private Integer age;
	private Gender gender;

	@NotBlank
	@Pattern(regexp = "\\d+[A-F]")
	private String seat;
	
	@NotBlank
	private Integer flightTripId;

	public PassengerDTO() {
		super();
	}

	public PassengerDTO(String passengerId,String name, Integer age, Gender gender, String seat,
			Integer flightTripId) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seat = seat;
		this.flightTripId = flightTripId;
	}

	public PassengerDTO(String passengerId, @NotBlank String name, @PositiveOrZero Integer age, Gender gender,
			@NotBlank @Pattern(regexp = "\\d+[A-F]") String seat) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seat = seat;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
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
