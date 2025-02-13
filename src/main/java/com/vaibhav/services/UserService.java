package com.vaibhav.services;

import com.vaibhav.binding.LoginForm;
import com.vaibhav.binding.SignUpForm;
import com.vaibhav.binding.UnlockForm;

public interface UserService 

{

	//public String login(LoginForm form);
	public boolean signUp(SignUpForm form);
	//public boolean unlockAccount(UnlockForm form);
	//public String forgotPwd(String email); 
}
 