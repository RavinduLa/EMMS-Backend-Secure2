package com.emms.model;

import java.util.Set;

public class UserResponse {
	
	private String username;
	private boolean enabled;
	private Set<Role> roles;
	
	public UserResponse() {
		
	}

	

	public UserResponse(String username, boolean enabled, Set<Role> roles) {
		super();
		this.username = username;
		this.enabled = enabled;
		this.roles = roles;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "UserReponse [username=" + username + ", enabled=" + enabled + ", roles=" + roles + "]";
	}



	
	
	
	
	

}
