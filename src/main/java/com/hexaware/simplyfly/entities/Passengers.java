package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Passengers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passengerId;
	
	@Column(nullable = false)

	@NotBlank
	//@Pattern(regexp = "^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$")
	private String name;
	
	@Min(value = 0)
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ManyToMany(mappedBy = "passengers",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private Set<Bookings> bookings = new HashSet<>();

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "flightTripId", referencedColumnName = "flightTripId"),
			@JoinColumn(name = "seatNo", referencedColumnName = "seatNo") })
	@JsonIgnore
	private Seats seat;

	public Passengers() {
		super();
	}

	public Passengers(Integer passengerId, String name, Integer age, Gender gender, Set<Bookings> bookings,
			Seats seat) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.bookings = bookings;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

}
