package com.emms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import com.emms.dal.adapter.UserDataAdapter;
import com.emms.model.IndividualOldPasswordCheckRequest;
import com.emms.model.User;

@Service
public class IndividualUserApi {
	
	private UserDataAdapter userDataAdapter;
	
	@Autowired
	public IndividualUserApi(UserDataAdapter userDataAdapter) {
		this.userDataAdapter = userDataAdapter;
	}
	
	public void changePassword() {
		
	}
	
	//method for checking whether the old password is correct
	public boolean isOldPasswordCorrect(IndividualOldPasswordCheckRequest passwordCheckRequest) {
		User user = userDataAdapter.getUserByUsername(passwordCheckRequest.getUsername());
		
		//get the old password in plaintext
		String rawcandidatePassword = passwordCheckRequest.getOldPassword();
		
		//get the hashed original password
		String originalPassword = user.getPassword();
		
		//check whether the palintext candidate matches with the stored hashed value
		if(BCrypt.checkpw(rawcandidatePassword, originalPassword))
		{
			return true;
		}
		else {
			return false;
		}
	}

}
