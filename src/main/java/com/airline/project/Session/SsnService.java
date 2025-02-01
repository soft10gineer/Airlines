package com.airline.project.Session;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SsnService {
	
	@Autowired
	private SessionRepository sessionrepository;
	
	public String sessionCreation(String refId) {
		
		System.out.println("Session ref number "+ refId);
		LocalDateTime date = LocalDateTime.now();
		
		SsnDtls session = new SsnDtls(refId, date);
		sessionrepository.save(session);
		return "String";
	}
		
}
