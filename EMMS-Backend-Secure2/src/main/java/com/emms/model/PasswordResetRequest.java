package com.emms.model;

public class PasswordResetRequest {
	
	private int id;
	private String newPassword;
	
	public PasswordResetRequest() {
		
	}
	
	public PasswordResetRequest(int id, String newPassword) {
		super();
		this.id = id;
		this.newPassword = newPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordResetRequest [id=" + id + ", newPassword=" + newPassword + "]";
	}
	
	
	

}
