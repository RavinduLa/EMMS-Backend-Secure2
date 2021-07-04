package com.emms.model;

import java.util.Set;

public class RegistrationRequest {
	
	private String username;
	private String password;
	private Set<Role> roles;
	
	public RegistrationRequest() {
		
	}

	public RegistrationRequest(String username, String password, Set<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
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
	
	

}
