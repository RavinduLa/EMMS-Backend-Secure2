package com.emms.dal.adapterImpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.dal.adapter.UserDataAdapter;
import com.emms.dao.UserRepository;
import com.emms.model.User;
import com.emms.model.UserRoleUpdateRequest;

@Component
public class UserDataAdapterImpl implements UserDataAdapter {
	
	private UserRepository userRepository;

	@Autowired
	public UserDataAdapterImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		User savedUser =  userRepository.save(user);
		System.out.println("User saved. User: "+user.toString());
		return savedUser;
	}

	@Override
	public User update(User user) {
		//update the user
		//String username = user.getUsername();
		//User userToUpdate = userRepository.getUserByUsername(user.getUsername());
		User userToUpdate = userRepository.findById(user.getId()  ).get()   ;
		userToUpdate.setRoles(user.getRoles());
		userToUpdate.setEnabled(user.isEnabled());
		User updatedUser = userRepository.save(userToUpdate);
		return updatedUser;
	}

	@Override
	public int delete(int id) {
		userRepository.deleteById(id);
		System.out.println("Deleted user with id : " + id);
		return id;
	}

	@Override
	public List<User> getAll() {
		System.out.println("Returning all users");
		return userRepository.findAll();
	}

	@Override
	public int enableUser(int id) {
		User user = userRepository.findById(id).get();  //get the user by id
		user.setEnabled(true);  //enable the user
		userRepository.save(user);  //save the user
		return id;
	}

	@Override
	public int disableUser(int id) {
		//get the user by id
		User user = userRepository.findById(id).get();
		//disable the user
		user.setEnabled(false);
		//save the user
		userRepository.save(user);
		return id;
	}

	@Override
	public User getUserById(int id) {
		System.out.println("Returning user for id : "+id);
		
		try {
			User user = userRepository.findById(id).get();
			System.out.println("User found. Returning user : "+user);
			return user;
		}catch(NoSuchElementException e) {
			System.out.println("User not found! Returning empty User object");
			return new User();
		}
		
	}

	@Override
	public User updateByUsername(UserRoleUpdateRequest userRoleUpdateRequest) {
		System.out.println("Updating User roles with username : "+userRoleUpdateRequest.getUsername());
		User user = userRepository.getUserByUsername(userRoleUpdateRequest.getUsername());
		user.setRoles(userRoleUpdateRequest.getRoles());
		return userRepository.save(user);
	}

	//get user by the username
	@Override
	public User getUserByUsername(String username) {
		
		return userRepository.getUserByUsername(username);
	}


}
