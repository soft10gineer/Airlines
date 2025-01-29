package com.airline.project.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userrepository;
	
	@GetMapping("/id")
	public String getUserById(@RequestBody Map<String, String> requestBody) throws JsonProcessingException {
		
		String userId = (String) requestBody.get("id");
		return userService.getUserById(userId);
	}
	
	@GetMapping("/all")
	public String getAllUser() throws JsonProcessingException {
		return userService.getAllUser();
	}
	
	
	
	@GetMapping("/get")
	public ResponseEntity<?> getUserByName(@RequestBody Map<String, String> userName) throws JsonProcessingException {
	        String userNameSearch = userName.get("name");
	        String user = userService.findUserByName(userNameSearch);
	        	
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.status(404).body("No result found");
	        } 
	    }
	
	@DeleteMapping("/delete-user")
	public String deleteUserByName(@RequestBody Map<String, String> userDelete) {
		String userName = (String) userDelete.get("name");
		return userService.deleteUser(userName);
	}
}
 