package com.emms.model;

import java.util.Set;

public class UserRoleUpdateRequest {
	
	private String username;
	private Set<Role> roles;
	
	public UserRoleUpdateRequest() {
		
	}
	
	public UserRoleUpdateRequest(String username, Set<Role> roles) {
		super();
		this.username = username;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserRoleUpdateRequest [username=" + username + ", roles=" + roles + "]";
	}
	
	
	
	

}
