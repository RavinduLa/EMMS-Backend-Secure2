package com.emms.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.UserApi;
import com.emms.model.PasswordResetRequest;
import com.emms.model.RegistrationRequest;
import com.emms.model.User;
import com.emms.model.UserResponse;
import com.emms.model.UserRoleUpdateRequest;
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
	
	@PutMapping("enable/{id}")
	public int enableUser(@PathVariable int id) {
		return userApi.enableUser(id);
	}
	
	@PutMapping("disable/{id}")
	public int disabelUser(@PathVariable int id) {
		return userApi.disableUser(id);
	}
	
	@GetMapping("get-user/{id}")
	public UserResponse getUserById(@PathVariable int id) {
		return userApi.getUseById(id);
	}
	
	@PutMapping("updateUserByUsername")
	public UserResponse updateUserById(@RequestBody UserRoleUpdateRequest userRoleUpdateRequest) {
		System.out.println("Controller -  username : "+ userRoleUpdateRequest.getUsername());
		return userApi.updateUserByUsername(userRoleUpdateRequest);
	}
	
	@DeleteMapping("deleteUser/{id}")
	public int deleteUserById(@PathVariable int id) {
		return userApi.deleteUser(id);
	}
	
	@PutMapping("resetPassword")
	public UserResponse resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
		System.out.println("Controller reset request : " +passwordResetRequest.getId() );
		return userApi.resetPassword(passwordResetRequest);
	}
	
	@GetMapping("isUsernameAvailable/{username}")
	public boolean isUsernameAvailable(@PathVariable String username) {
		return userApi.isUsernameAvailable(username);
	}

}
