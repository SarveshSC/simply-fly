package com.hexaware.simplyfly.dto;

public class AirlinesDto {
	String airlineId;
	String airlineName;
	
	public AirlinesDto() {
		super();
	}
	public AirlinesDto(String airlineId, String airlineName) {
		super();
		this.airlineId = airlineId;
		this.airlineName = airlineName;
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
