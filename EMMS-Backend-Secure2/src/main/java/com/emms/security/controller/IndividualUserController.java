package com.emms.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.UserApi;
import com.emms.model.UserResponse;

@RestController
@RequestMapping("api/individualUser/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class IndividualUserController {
	
	private UserApi userApi;
	
	public IndividualUserController(UserApi userApi) {
		this.userApi = userApi;
	}
	
	@GetMapping("getUser/{username}")
	public UserResponse getUserByUsername(@PathVariable String username) {
		return userApi.getUserByUsername(username);
	}

}
