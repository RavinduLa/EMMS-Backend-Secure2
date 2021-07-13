package com.emms.dal.adapter;

import java.util.List;

import com.emms.model.User;
import com.emms.model.UserRoleUpdateRequest;

public interface UserDataAdapter {
	
	public List<User> getAll();
	public User save(User user);
	public User update(User user);
	public User updateByUsername(UserRoleUpdateRequest userRoleUpdateRequest);
	public int enableUser(int id);
	public int disableUser(int id);
	public int delete(int id);
	public User getUserById(int id);

}
