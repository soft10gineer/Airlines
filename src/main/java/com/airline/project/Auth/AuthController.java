package com.airline.project.Auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.project.Login.UserLoginRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserLoginRepository userloginrepository;
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello";
	}
	
	@GetMapping("/id")
	public String getUserRef(@RequestBody Map<String, Object> userBody) {
		
		String userRef = (String) userBody.get("Ref");
		return userloginrepository.findById(userRef).get().getUserKey();
		
	}
	
	
}
