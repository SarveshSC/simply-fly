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

import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.FlightDetailsRepo;


@SpringBootTest
class AirPlaneOwnerImplTest {
	
	
	@Autowired
	FlightDetailsRepo flightdetailsRepo;
	
	@Autowired
	AirPlaneOwnerImpl service;

	@Test
	@Disabled
	void testAddFlights() {
		Flights flight1=new Flights("AIR-60", 180);

		service.addFlights(flight1, "SG");
		assertTrue(true);
	}
	@Test
	@Disabled
	void testAddFlightDetails() {
		String departure = "2024-02-05T05:00";
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
      String arrival="2024-02-05T08:00";
      LocalDateTime departureDateTime = LocalDateTime.parse(departure, formatter);
      LocalDateTime arrivalDateTime = LocalDateTime.parse(arrival, formatter);
		
      FlightDetails flightDetails=new FlightDetails(135, departureDateTime, arrivalDateTime, 10000D, 50);
      
      
      Flights flights=service.addFlightDetails(flightDetails, "AIR-60", "BLR", "DEL");
	assertEquals("SpiceJet", flights.getAirline().getAirlineName());
	
	
	}
	@Test
	@Disabled
	void testDelete() {
		service.deleteFlightDetails(135);
	}
	
	
	@Test
	@Disabled
	void testUpdate()
	{
		FlightDetails flightdeatils=flightdetailsRepo.findById(135).orElse(null);
		flightdeatils.setTicketPrice(20000D);
		FlightDetails flightdetails1=service.updateFlightDetails(flightdeatils);
		assertEquals(20000D, flightdetails1.getTicketPrice());
	}
	
	
}
