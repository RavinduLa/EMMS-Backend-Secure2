package com.emms.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.UserApi;
import com.emms.model.RegistrationRequest;
import com.emms.model.User;
import com.emms.model.UserUpdateRequest;

@RestController
@RequestMapping("api/user/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class UserController {
	
	private UserApi userApi;

	@Autowired
	public UserController(UserApi userApi) {
		this.userApi = userApi;
	}
	
	
	@PostMapping("register")
	public User registerUser(@RequestBody RegistrationRequest registrationRequest) {
		return userApi.addUser(registrationRequest);
	}
	
	@GetMapping("get-all-users")
	public List<User> getALlUsers(){
		return userApi.getAllUsers();
	}
	
	@PutMapping("update")
	public User updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
		return userApi.updateUser(userUpdateRequest);
	}
	

}
