package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Passengers {
	@Id
	private Integer passengerId;
	private String name;
	private Integer age;
	private String gender;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="bookings_passengers" , joinColumns = {@JoinColumn(name="passengerId")} ,
       inverseJoinColumns = {@JoinColumn(name="bookingId")} )
    private  Set<Bookings> bookings  = new HashSet<Bookings>();
	
	@OneToOne
	@JoinColumn(name="seatNo")
	private Seats seat;
	
	public Passengers() {
		super();
	}

	public Passengers(Integer passengerId, String name, Integer age, String gender) {
		super();
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
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
	
}
