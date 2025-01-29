package com.airline.project.OtpDetails;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	@Autowired
	private OtpRepository otprepository;
	
	public String generateOtp(String refId) {
		
		String otp = "";
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			int rand_int = rand.nextInt(9);
			otp = otp + Integer.toString(rand_int);
			  
			}
		
		if(otprepository.findById(refId).isEmpty()) {
			
		OtpDtls otpInstance = new OtpDtls();
		otpInstance.setGeneratedOtp(otp);
		otpInstance.setOtpGenerateTime(LocalDateTime.now());
		otpInstance.setRefId(refId);
		Integer otpAttempt = 1;
		otpInstance.setOtpAttempt(otpAttempt);
		otprepository.save(otpInstance);
		
		return "Otp Generated Succesfully";
		
	} else { 
		
		String resendOtp = "";
		Random randOtp = new Random();
		for (int i = 0; i < 6; i++) {
			int rand_int = rand.nextInt(9);
			resendOtp = resendOtp + Integer.toString(rand_int);
			}
		
		OtpDtls otpInstance = otprepository.findById(refId).get();
		otpInstance.setGeneratedOtp(resendOtp);
		otpInstance.setOtpGenerateTime(LocalDateTime.now());
		otpInstance.setRefId(refId);
		Integer otpAttempt = otpInstance.getOtpAttempt()+1;
		otpInstance.setOtpAttempt(otpAttempt);
		otprepository.save(otpInstance);
		return "Otp Resended Successfully";
	}
	}
	
	public String validateOtp(String otp, String leadid) {
		OtpDtls user = otprepository.findById(leadid).get();
		if (user.getGeneratedOtp().equals(otp)) {
			user.setUsrVldty(true);
			otprepository.save(user);
			return "User Validated Successfully";
		} else { 
			return "Invalid OTP! Please Try again";
		}
	}
	
	public String resendOtp(String refId) {
		Integer otpAttempts = otprepository.findById(refId).get().getOtpAttempt();
		if(otpAttempts==3) {
			
			return "OTP Limits Exceeded";
		
	} else {
		return generateOtp(refId);
		}
	}
}