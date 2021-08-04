package com.emms.model;

import java.util.Set;

public class UserResponse {
	
	private int id;
	private String username;
	private boolean enabled;
	private Set<Role> roles;
	
	public UserResponse() {
		
	}




	public UserResponse(int id, String username, boolean enabled, Set<Role> roles) {
		super();
		this.id = id;
		this.username = username;
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
		return "UserResponse [id=" + id + ", username=" + username + ", enabled=" + enabled + ", roles=" + roles + "]";
	}



	
	
	
	
	

}
