package com.airline.project.Flights;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="airlines_names", schema="air_name")
public class FlightDtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="air_id")
	private Integer flightId;
	
	@Column(name="air_name")
	private String flightName;

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	
	
}
