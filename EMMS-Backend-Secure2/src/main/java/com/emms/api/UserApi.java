package com.emms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.UserDataAdapter;
import com.emms.model.PasswordResetRequest;
import com.emms.model.RegistrationRequest;
import com.emms.model.User;
import com.emms.model.UserResponse;
import com.emms.model.UserRoleUpdateRequest;
import com.emms.model.UserUpdateRequest;

@Service
public class UserApi {
	
	private UserDataAdapter userDataAdapter;

	@Autowired
	public UserApi(UserDataAdapter userDataAdapter) {
		this.userDataAdapter = userDataAdapter;
	}
	
	public User addUser(RegistrationRequest registrationRequest) {
		
		User user = new User();
		user.setUsername(registrationRequest.getUsername());
		System.out.println("Roles : "+registrationRequest.getRoles());
		user.setRoles(registrationRequest.getRoles());
		user.setEnabled(true);
		
		System.out.println("API: User details set");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = registrationRequest.getPassword();
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println("API: Password encoded");
		
		user.setPassword(encodedPassword);
		
		System.out.println("Password set");
		
		return userDataAdapter.save(user);
	}
	
	public List<User> getAllUsers(){
		System.out.println("API : getting all users");
		return userDataAdapter.getAll();
	}
	
	public User updateUser(UserUpdateRequest userUpdateRequest) {
		System.out.println("API : Updating user with username : " + userUpdateRequest.getUsername());
		
		//setting user object
		User user = new User();
		user.setId(userUpdateRequest.getId());
		user.setUsername(userUpdateRequest.getUsername());
		user.setRoles(userUpdateRequest.getRoles());
		user.setEnabled(userUpdateRequest.isEnabled());
		
		//calling update in data adapter
		return userDataAdapter.update(user);
	}
	
	public int enableUser(int id) {
		return userDataAdapter.enableUser(id);
	}
	
	public int disableUser(int id) {
		return userDataAdapter.disableUser(id);
	}
	
	public UserResponse getUseById(int id) {
		
		//instantiate user object
		User user = userDataAdapter.getUserById(id);
		
		//instantiate UserResponse object
		UserResponse userResponse = new UserResponse();
		
		//set the object attributes
		userResponse.setId(user.getId());
		userResponse.setUsername(user.getUsername());
		userResponse.setEnabled(user.isEnabled());
		userResponse.setRoles(user.getRoles());
		
		return userResponse;
	}
	
	public UserResponse updateUserByUsername(UserRoleUpdateRequest userRoleUpdateRequest) {
		
		//instantiate user object
		User user = userDataAdapter.updateByUsername(userRoleUpdateRequest);
		
		//instantiate UserResponse object
		UserResponse userResponse = new UserResponse();
		
		//set the object attributes
		userResponse.setId(user.getId());
		userResponse.setUsername(user.getUsername());
		userResponse.setEnabled(user.isEnabled());
		userResponse.setRoles(user.getRoles());
		
		return userResponse;
	}
	
	//delete a user
	public int deleteUser(int id) {
		return userDataAdapter.delete(id);
	}
	
	//reset the password of a user.
	public UserResponse resetPassword(PasswordResetRequest resetRequest) {
		
		//get the user for the id
		System.out.println("Resetting Password...");
		System.out.println("Requesting User object for id: "+ resetRequest.getId());
		User user = userDataAdapter.getUserById(resetRequest.getId());
		//instantiate encoder
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//get raw password
		String rawPassword = resetRequest.getNewPassword();
		//encode the password
		String encodedPassword = encoder.encode(rawPassword);
		
		//set the new encoded password
		user.setPassword(encodedPassword);
		
		//save the updated user
		User updatedUser = userDataAdapter.update(user);
		
		//instantiate and set the user response
		UserResponse userResponse = new UserResponse();
		userResponse.setId(updatedUser.getId());
		userResponse.setUsername(updatedUser.getUsername());
		userResponse.setRoles(updatedUser.getRoles());
		userResponse.setEnabled(updatedUser.isEnabled());
		
		return userResponse;
		
	}
	
	public UserResponse getUserByUsername(String username) {
		//get the user by the username
		User user = userDataAdapter.getUserByUsername(username);
		
		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setUsername(user.getUsername());
		userResponse.setRoles(user.getRoles());
		userResponse.setEnabled(user.isEnabled());
		
		return userResponse;
	}
	

}
