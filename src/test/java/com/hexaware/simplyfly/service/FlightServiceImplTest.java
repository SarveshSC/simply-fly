package com.hexaware.simplyfly.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import com.hexaware.simplyfly.dto.FlightsDto;
import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.AirportRepository;
import com.hexaware.simplyfly.repository.FlightDetailsRepository;


@SpringBootTest
class FlightServiceImplTest {
	
	@Autowired
	AirportRepository airPortRepo;
	
	@Autowired
	FlightDetailsRepository flightdetailsRepo;
	
	@Autowired
	FlightServiceImpl service;

	@Test
	@Disabled
	void testAddFlights() {
		FlightsDto flightDto=new FlightsDto("AIR-58", 180);
		Flights flight=service.addFlights(flightDto, "BLR", "HYD", "AI");
		assertEquals("MAA", flight.getDestination().getAbbrevation());
	}
	
	
	
	@Test
	@Disabled
	void testUpdateFlights() {
		FlightsDto flightDto=new FlightsDto("AIR-52", 180);
		Flights flight=service.updateFlights(flightDto, "HYD", "DEL", "AI");
		assertEquals("DEL", flight.getDestination().getAbbrevation());
	}
	
	
	@Test
	@Disabled
	void testDeleteFlights() {
		service.removeFlights("AIR-52");
		
	}
	
	
	@Test
	@Disabled
	void testGetFlightsBySource() {
		service.flightsBySource("HYD");
		assertTrue(true);
	}
	
	
	@Test
	void testGetFlightByDestination() {
		service.flightsByDestination("HYD");
		assertTrue(true);
	}
	
	
	
	
}
	