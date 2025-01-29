package com.airline.project.OtpDetails;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.project.User.UserOnboarding;

@RestController
@RequestMapping("/otp")
public class OtpController {
	
	@Autowired
	public OtpService otpservice;
	
	@GetMapping("/regenerate")
	public String regenerateOtp(@RequestBody UserOnboarding user) {
		return otpservice.resendOtp(user.getUserId());
	}
	
	@GetMapping("/validate")
	public String validateOtp(@RequestBody Map<String, Object> otpDetails)  {
		String userOtp = (String) otpDetails.get("Otp");
		String leadId = (String) otpDetails.get("LeadId");
		return otpservice.validateOtp(userOtp, leadId);
		
	}

}
