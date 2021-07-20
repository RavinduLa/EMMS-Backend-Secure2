package com.emms.model;

public class IndividualOldPasswordCheckRequest {
	
	private String username;
	private String oldPassword;
	
	public IndividualOldPasswordCheckRequest() {
		
	}
	
	public IndividualOldPasswordCheckRequest(String username, String oldPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@Override
	public String toString() {
		return "IndividualOldPasswordCheckRequest [username=" + username + ", oldPassword=" + oldPassword + "]";
	}
	
	
	

}
