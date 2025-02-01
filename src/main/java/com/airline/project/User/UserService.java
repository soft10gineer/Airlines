package com.airline.project.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.project.OtpDetails.OtpDtls;
import com.airline.project.OtpDetails.OtpRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OtpRepository otprepository;
	
	@Autowired
	private ObjectMapper objectmapper ;
	
	public String getUserById(String userId) throws JsonProcessingException {
		
		Optional<UserOnboarding> findUser = userRepository.findById(userId);
		String userName = findUser.get().getUserFirstName();
		return objectmapper.writeValueAsString(findUser);
		
	}
	
	public String findUserByName(String name) throws JsonProcessingException {
        UserOnboarding searchedUser = userRepository.findByUserName(name);
		return objectmapper.writeValueAsString(searchedUser);
		
    }
	
	public String getAllUser() throws JsonProcessingException {
		
		List<UserOnboarding> allUser = userRepository.findAll();

		return objectmapper.writeValueAsString(allUser);
	}
	
	public String deleteUser(String userName) {
		UserOnboarding deleteUser = userRepository.findByUserName(userName);
		if(deleteUser.getUserId().isEmpty()) {	
			return "User Doesnot Exist";
		} else {
			userRepository.delete(deleteUser);
			return "User Deleted Succesfully";
		}
	}
	
	public String prsnlDtlsInput(UserOnboarding userBody) {
		System.out.println("user :" + userBody.getUserId());
		Optional<OtpDtls> userOtp =  otprepository.findById(userBody.getUserId());
		UserOnboarding user = userRepository.findById(userBody.getUserId()).get();
		
		if( userOtp.isPresent() && userOtp.get().getUsrVldty().equals(true)) {
			userBody.setCreatedDateTimestamp(user.getCreatedDateTimestamp());
			userBody.setUserMobileNumber(user.getUserMobileNumber());
			userRepository.save(userBody);
			return "User Details of "+userBody.getUserFirstName()+ "is saved......";} 
		else { 
			return "User is not present";
		}
	}
	
	public String addUser(UserOnboarding userBody) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(formatter);
        
		char randomLowercase = (char) ('a' + new Random().nextInt(26));
        char randomUppercase = (char) ('A' + new Random().nextInt(26));
        
        String variable = "AIR" + formattedDateTime + randomLowercase + randomUppercase ;
		userBody.setUserId(variable);
		String mobileNumber = "+91"+userBody.getUserMobileNumber();
		userBody.setUserMobileNumber(mobileNumber);
        LocalDateTime createdDate = LocalDateTime.now();
        userBody.setCreatedDateTimestamp(createdDate);
        userRepository.save(userBody);
		return variable;	
	}

}
 