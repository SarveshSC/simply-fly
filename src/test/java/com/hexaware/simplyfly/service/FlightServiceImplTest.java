package com.hexaware.simplyfly.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightTripRepository;


@SpringBootTest
class FlightServiceImplTest {
	
	@Autowired
	AirportRepository airPortRepo;
	
	@Autowired
	FlightTripRepository flightTripRepo;
	
	@Autowired
	FlightServiceImpl service;

//	@Test
//	@Disabled
//	void testAddFlights() {
//		FlightDTO flightDto=new FlightDTO("AIR-58", 180);
//		Flights flight=service.addFlights(flightDto, "BLR", "HYD", "AI");
//		assertEquals("MAA", flight.getDestination().getAbbrevation());
//	}
//	
//	
//	
//	@Test
//	@Disabled
//	void testUpdateFlights() {
//		FlightDTO flightDto=new FlightDTO("AIR-52", 180);
//		Flights flight=service.updateFlights(flightDto, "HYD", "DEL", "AI");
//		assertEquals("DEL", flight.getDestination().getAbbrevation());
//	}
//	
//	
//	@Test
//	@Disabled
//	void testDeleteFlights() {
//		service.removeFlights("AIR-52");
//		
//	}
//	
//	
//	@Test
//	@Disabled
//	void testGetFlightsBySource() {
//		service.flightsBySource("HYD");
//		assertTrue(true);
//	}
//	
//	
//	@Test
//	void testGetFlightByDestination() {
//		service.flightsByDestination("HYD");
//		assertTrue(true);
//	}
//	
	
	
	
}
	