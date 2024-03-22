package com.hexaware.simplyfly.restcontroller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.simplyfly.dto.FlightDTO;
import com.hexaware.simplyfly.entities.Flights;
import com.hexaware.simplyfly.exception.AirlineNotFoundException;
import com.hexaware.simplyfly.exception.UserNotFoundException;
import com.hexaware.simplyfly.repository.UserRepository;
import com.hexaware.simplyfly.service.IFlightService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/simply-fly/flights")

public class FlightServiceController {
	@Autowired
	IFlightService service;
	
	@Autowired
	UserRepository userRepo;


	@PostMapping("/add-flights/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public Flights addFlights(@RequestBody @Valid FlightDTO flightDto, @PathVariable String username) throws Exception {
		return service.addFlights(flightDto, username);
	}


	@PutMapping("/update-flights/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public Flights updateFlights(@RequestBody @Valid FlightDTO flightDto, @PathVariable String username) throws Exception {

		return service.updateFlights(flightDto, username);
	}


	@DeleteMapping("/delete-flights/{username}/{flightId}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public String removeFlights(@PathVariable String username, @PathVariable String flightId) throws Exception {
		return service.removeFlights(flightId, username);
	}

	@GetMapping("/get-flights-by-username/{username}")
	@PreAuthorize("hasAuthority('FlightOwner')")
	public List<FlightDTO> getFlightsByUsername(@PathVariable String username) throws AirlineNotFoundException
	{
		return service.viewAllFlightsByUsername(username);
	}
	
	
	

	@GetMapping("/get-all-flights")
	@PreAuthorize("hasAnyAuthority('FlightOwner','Admin')")
	public List<Flights> getAllFlights(){
		return service.viewAllFlights();
	}
	
	
	@PostMapping("/upload-logo/{username}")
	@PreAuthorize("hasAnyAuthority('FlightOwner','Admin')")
	public String uploadLogo(@PathVariable String username,@RequestParam("logo") MultipartFile file) throws UserNotFoundException, IOException{
		System.out.println("the method of upload in the contrroller is being called");
		return service.uploadLogo(username, file);
	}
		
	
//	@GetMapping("/get-the-logo/{username}")
//	@PreAuthorize("hasAnyAuthority('FlightOwner','Admin')")
//	public ResponseEntity<?> downloadImage(@PathVariable String username) throws UserNotFoundException{
//		byte[] logo=service.downloadLogo(username);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/jpeg"))
//				.body(logo);
//	}
	
	@GetMapping("/get-the-logo/{username}")
	@PreAuthorize("hasAnyAuthority('FlightOwner','Admin')")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String username) throws UserNotFoundException {
	    byte[] logo = service.downloadLogo(username);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.valueOf(userRepo.findById(username).get().getLogoContentTtype()));
	    return new ResponseEntity<>(logo, headers, HttpStatus.OK);
	}

}
