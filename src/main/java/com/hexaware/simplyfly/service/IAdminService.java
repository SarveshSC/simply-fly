package com.hexaware.simplyfly.service;

import com.hexaware.simplyfly.dto.AirportDTO;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.exception.AirportNotFoundException;

public interface IAdminService {
	public Airports addAirport(AirportDTO airportDTO);
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException;
	public void removeAirport(String airportCode);
	
}
