package com.emms.model;

import java.util.Set;

public class UserUpdateRequest {
	
	private int id;
	private String username;
	private String password;
	private boolean enabled;
	private Set<Role> roles;
	
	public UserUpdateRequest() {
		
	}

	



	public UserUpdateRequest(int id, String username, String password, boolean enabled, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	

}
