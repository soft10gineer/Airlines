package com.airline.project.Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.project.OtpDetails.OtpService;
import com.airline.project.Service.UserDetailsServiceImpl;
import com.airline.project.User.UserOnboarding;
import com.airline.project.User.UserRepository;
import com.airline.project.User.UserService;
import com.airline.project.Utils.JwtUtil;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserLoginRepository userloginrepository;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private OtpService otpservice;
	
	@GetMapping("/user")
	public String getLogin(@RequestBody UserLoginRegister user) {
		authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPasswrd()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserEmail());
		String jwtTOken =  jwtutil.generateToken(userDetails.getUsername());
		return jwtTOken;
		
	}
	
	@GetMapping("/test")
	public String test(@RequestBody UserLoginRegister user) {
		
		String test = user.getUserEmail();
		return userrepository.findByEmail(test).getUserId();
	}
	
	
	@PostMapping("/register")
	public String registerMobile(@RequestBody UserOnboarding user) {
		
		userservice.addUser(user);
		return otpservice.generateOtp(user.getUserId());
		
	}
	
}
