package com.airline.project.Login;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.airline.project.User.UserOnboarding;

@Service
public class LoginService {

	public String registerMobile(@RequestBody UserOnboarding user) {
		
		return "String";
	}
}
