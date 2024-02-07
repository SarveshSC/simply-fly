package com.hexaware.simplyfly.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.simplyfly.entities.FlightDetails;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.repository.FlightDetailsRepository;


@SpringBootTest
class AirPlaneOwnerImplTest {
	
	
	@Autowired
	FlightDetailsRepository flightdetailsRepo;
	
	@Autowired
	AirPlaneOwnerImpl service;

	@Test
	@Disabled
	void testAddFlights() {
	}
	@Test
	@Disabled
	void testAddFlightDetails() {
	
	
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
