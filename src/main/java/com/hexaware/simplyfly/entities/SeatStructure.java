package com.hexaware.simplyfly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SeatStructure {
	@Id
	private String seatNo;

	public String getSeatNo() {
		return seatNo;
	}

	public SeatStructure() {
		super();
	}

	public SeatStructure(String seatNo) {
		super();
		this.seatNo = seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

}
