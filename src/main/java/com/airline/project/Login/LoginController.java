package com.airline.project.Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.airline.project.Service.UserSessionServiceImpl;
import com.airline.project.Session.SessionRepository;
import com.airline.project.Session.SsnService;
import com.airline.project.User.UserOnboarding;
import com.airline.project.User.UserRepository;
import com.airline.project.User.UserService;
import com.airline.project.Utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserLoginRepository userloginrepository;
	
	@Autowired
	private SsnService ssnservice;
	
	@Autowired
	@Qualifier("UserDetailsServiceImpl")
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private UserSessionServiceImpl userSessionService;
	
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
	private ObjectMapper objectmapper;
	
	@Autowired
	private SessionRepository sessionrepository;
	
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
	
	@GetMapping("/generate")
	public String generateSessionToken(@RequestBody UserOnboarding user) {
		String generatedRefId = userservice.addUser(user);
		
		System.out.println("generated ref id :-"+ generatedRefId);
		
		System.out.println(ssnservice.sessionCreation(generatedRefId));
		
		String userSessionRefNo = sessionrepository.findById(generatedRefId).get().getSsnRefNo();
		System.out.println("Sessoion :- "+userSessionRefNo);
		UserDetails userSession = userSessionService.loadUserByUsername(userSessionRefNo);
		System.out.println("user session :-"+userSessionRefNo);
		String sessionJwtToken = jwtutil.generateToken(userSession.getUsername());
		return sessionJwtToken;
	}
	
	@PostMapping("/verify-mobile")
	public String generateOtp(@RequestBody Map<String, String> userLeadId) {
		String leadID = (String) userLeadId.get("leadId");
		return otpservice.generateOtp(leadID);
		
	}
	
	@PostMapping("/validate-mobile")
	public String validateOtp(@RequestBody Map<String, Object> userLeadId) {
		String inputOtp = (String) userLeadId.get("Otp");
		String leadID = (String) userLeadId.get("leadId");
		
		return otpservice.validateOtp(inputOtp, leadID);
		
	}
	
	@PostMapping("/prsnl-dtls")
	public String usrPrsnlDtlsInput(@RequestBody UserOnboarding user) {
		return userservice.prsnlDtlsInput(user);
		
	}
	
	@PostMapping("/register")
	public String registerMobile(@RequestBody UserOnboarding user) throws JsonProcessingException {
		
		String serviceOne = userservice.addUser(user);
		return "User Registered Succesfully with Leadid : "+ serviceOne;
	}
	
}
