package com.emms.dal.adapterImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emms.dal.adapter.UserDataAdapter;
import com.emms.dao.UserRepository;
import com.emms.model.User;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getAll() {
		System.out.println("Returning all users");
		return userRepository.findAll();
	}

}
