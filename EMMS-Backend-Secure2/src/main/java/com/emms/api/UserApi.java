package com.emms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.UserDataAdapter;
import com.emms.model.RegistrationRequest;
import com.emms.model.User;
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
	
	

}
