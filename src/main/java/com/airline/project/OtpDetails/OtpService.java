package com.airline.project.OtpDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.airline.project.User.UserOnboarding;
import com.airline.project.User.UserRepository;

@Service
public class OtpService {
	
	@Autowired
	private OtpRepository otprepository;
	
	@Autowired
	private UserRepository userrepository;
	
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
		UserOnboarding userOnb = userrepository.findById(leadid).get();
		LocalDateTime otpTime = user.getOtpGenerateTime();
		LocalDateTime userOtpTime = LocalDateTime.now();
		
		Duration duration = Duration.between(otpTime, userOtpTime);
		if (duration.getSeconds()<=120) {
			 
			if (user.getGeneratedOtp().equals(otp)) {
				
				user.setUsrVldty(true);
				userOnb.setUserIsVerfied(true);
				otprepository.save(user);
				return "User Validated Successfully";
			}  else { 
				return "Invalid OTP! Please Try again";
			}
			
		} else {
			return "Time Exceeded! Please Try Again";
		}
	}
	
	public String resendOtp(String refId) {
		Integer otpAttempts = otprepository.findById(refId).get().getOtpAttempt();
		LocalDateTime yearDuration = otprepository.findById(refId).get().getOtpGenerateTime();
		LocalDateTime dateNow = LocalDateTime.now();
		Duration duration = Duration.between(yearDuration, dateNow);
		if(otpAttempts==3) {
			
			return "OTP Limits Exceeded";
		
		} 
		else {
		return generateOtp(refId);
		}
	}
}