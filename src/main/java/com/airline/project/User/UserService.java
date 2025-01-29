package com.airline.project.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ObjectMapper objectmapper ;
	
	public String getUserById(String userId) throws JsonProcessingException {
		
		Optional<UserOnboarding> findUser = userRepository.findById(userId);
		String userName = findUser.get().getUserName();
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
	
	
	public String addUser(Map<String, Object> userBody) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmssSSS");
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(formatter);
        
		char randomLowercase = (char) ('a' + new Random().nextInt(26));
        char randomUppercase = (char) ('A' + new Random().nextInt(26));
        
        String variable = "AIR" + formattedDateTime + randomLowercase + randomUppercase ;
		String userName = (String) userBody.get("name");
		Integer userAge = (Integer) userBody.get("age");
		String userEmail = (String) userBody.get("email");
		LocalDateTime createdDate = LocalDateTime.now();
		String userGender = (String) userBody.get("gender");
		UserOnboarding useronboard = new UserOnboarding(variable,userName,userAge, createdDate, userGender, userEmail );
		userRepository.save(useronboard);
		return "User Added Succesfully with id" + variable;	
	}

}
 