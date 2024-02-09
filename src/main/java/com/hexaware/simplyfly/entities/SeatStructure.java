package com.hexaware.simplyfly.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SeatStructure {
	@Id
	private String seatNo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seatStructure")
	private Set<Seats> seats = new HashSet<>();

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
}
