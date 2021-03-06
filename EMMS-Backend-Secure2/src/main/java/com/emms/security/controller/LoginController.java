package com.emms.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emms.UserDetialsServiceImpl;
import com.emms.dao.UserRepository;
import com.emms.model.AuthenticationRequest;
import com.emms.model.AuthenticationResponse;
import com.emms.model.User;
import com.emms.model.UserResponse;
import com.emms.util.JwtUtil;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class LoginController {
	
	
	private AuthenticationManager authenticationManager;
	
	private UserDetialsServiceImpl userDetailsServiceImpl;
	
	private JwtUtil jwtUtil;
	
	private UserRepository userRepository;
	
	@Autowired
	public LoginController(AuthenticationManager authManager, UserDetialsServiceImpl userDetialsServiceImpl, JwtUtil jwtUtil,UserRepository userRepository) {
		this.authenticationManager = authManager;
		this.userDetailsServiceImpl = userDetialsServiceImpl;
		this.jwtUtil = jwtUtil;
		this.userRepository = userRepository;
	}
	
	
	@RequestMapping({"/"})
	public String home() {
		return "Home";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "Hello world";
	}
	
	@RequestMapping("/some")
	public String somePeople() {
		return "Hello Some people.";
	}
	
	@RequestMapping("/admin")
	public String adminHello() {
		return "hello admin";
	}
	
	@GetMapping("/get-user-details")
	public UserResponse getUserDetails(String username) {
		User user = userRepository.getUserByUsername(username);
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(user.getUsername());
		return userResponse;
	}
	
	//@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken (@RequestBody  AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username or password.",e);
		}
		
		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
		
		List<String> roles =  userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		String responseUsername = userDetails.getUsername();
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		System.out.println("Authenticating user: " + responseUsername);
		System.out.println("With roles: " + roles.toString());
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt,responseUsername,roles));
		
	}

}
