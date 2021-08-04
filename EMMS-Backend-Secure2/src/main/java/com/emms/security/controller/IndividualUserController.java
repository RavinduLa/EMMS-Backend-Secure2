package com.emms.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.IndividualUserApi;
import com.emms.api.UserApi;
import com.emms.model.IndividualOldPasswordCheckRequest;
import com.emms.model.PasswordResetRequest;
import com.emms.model.UserResponse;

@RestController
@RequestMapping("api/individualUser/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class IndividualUserController {
	
	private UserApi userApi;
	private IndividualUserApi individualUserApi;
	
	@Autowired
	public IndividualUserController(UserApi userApi, IndividualUserApi individualUserApi) {
		this.userApi = userApi;
		this.individualUserApi = individualUserApi;
	}
	
	@GetMapping("getUser/{username}")
	public UserResponse getUserByUsername(@PathVariable String username) {
		return userApi.getUserByUsername(username);
	}
	
	@PostMapping("checkOldPassword")
	public boolean checkOldPassword(@RequestBody IndividualOldPasswordCheckRequest passwordCheckRequest) {
		return individualUserApi.isOldPasswordCorrect(passwordCheckRequest);
	}
	
	@PutMapping("changePassword")
	public UserResponse changePassword(@RequestBody PasswordResetRequest passwordResetRequest) {
		return userApi.resetPassword(passwordResetRequest);
	}

}
