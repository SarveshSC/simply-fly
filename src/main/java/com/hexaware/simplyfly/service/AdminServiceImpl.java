package com.hexaware.simplyfly.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.simplyfly.dto.AirportDTO;
import com.hexaware.simplyfly.entities.Airports;
import com.hexaware.simplyfly.exception.AirportNotFoundException;
import com.hexaware.simplyfly.repository.AirportRepository;

public class AdminServiceImpl implements IAdminService {

	@Autowired
	AirportRepository airportRepo;

	@Override
	public Airports addAirport(AirportDTO airportDTO) {
		Airports airport = new Airports();
		airport.setName(airportDTO.getName());
		airport.setCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());

		return airportRepo.save(airport);
	}

	@Override
	public Airports updateAirport(AirportDTO airportDTO) throws AirportNotFoundException {
		Airports airport = airportRepo.findById(airportDTO.getIataCode()).orElseThrow(
				() -> new AirportNotFoundException("Airport with code " + airportDTO.getIataCode() + " not found."));
		airport.setName(airportDTO.getName());
		airport.setCode(airportDTO.getIataCode());
		airport.setLocation(airportDTO.getLocation());

		return airportRepo.save(airport);
	}

	@Override
	public void removeAirport(String airportCode) {
		airportRepo.deleteById(airportCode);
	}

}
