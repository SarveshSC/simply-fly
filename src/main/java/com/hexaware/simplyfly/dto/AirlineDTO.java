package com.hexaware.simplyfly.dto;

public class AirlineDTO {
	private String airlineId;
	private String airlineName;

	public AirlineDTO(String airlineId, String airlineName) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
	}

	public AirlineDTO() {
		super();
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

}
