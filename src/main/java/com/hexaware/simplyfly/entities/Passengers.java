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


@Entity
public class Passengers {
	@Id
	private Integer passengerId;
	private String firstName;
	private String lastName;
	private Integer age;
	private Gender gender;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="bookings_passengers_table" , joinColumns = {@JoinColumn(name="passengerId")} ,
       inverseJoinColumns = {@JoinColumn(name="bookingId")} )
    private  Set<Bookings> bookings  = new HashSet<Bookings>();
	
	public Passengers() {
		super();
	}

	public Passengers(Integer passengerId, String firstName, String lastName, Integer age, Gender gender) {
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
