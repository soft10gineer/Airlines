package com.airline.project.Flights;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlightController {
	
	@Autowired
	private FlightRepository flightrepository;
	
	@Autowired
	private ObjectMapper objectmapper;
	
	public String getAllFlight() throws JsonProcessingException {
		
		List<FlightDtls> flightDtls =   flightrepository.findAll();
		return objectmapper.writeValueAsString(flightDtls) ;
	}
}
