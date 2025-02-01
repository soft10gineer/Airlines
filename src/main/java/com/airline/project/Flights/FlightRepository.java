package com.airline.project.Flights;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightDtls, Integer>{

}
