package com.vaibhav.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.binding.LoginForm;
import com.vaibhav.binding.SignUpForm;
import com.vaibhav.binding.UnlockForm;
import com.vaibhav.constants.AppConstants;
import com.vaibhav.entites.UserDtls;
import com.vaibhav.repo.UserDtlsRepository;
import com.vaibhav.utility.EmailUtils;
import com.vaibhav.utility.PwdUtils;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImple implements UserService 
{
	@Autowired
	private UserDtlsRepository userDtlsRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm form) {
	    // Fetch user from the database
	    UserDtls entity = userDtlsRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());

	    if (entity == null) {
	        return AppConstants.INVALID_CREDENTIALS_MSG;  
	    }

	    if (AppConstants.STR_LOCKED.equalsIgnoreCase(entity.getAccountStatus())) {
	        return AppConstants.ACCOUNT_LOCKED_STATUS; 
	    }

	    // Debugging: Print user ID to confirm it's retrieved properly
	    System.out.println("User ID from DB: " + entity.getUserId());

	    // Ensure userId is not null before storing in session
	    if (entity.getUserId() == null) {
	        return AppConstants.USER_ID_ERROR;
	    }

	    // Store user ID in session
	    session.setAttribute(AppConstants.STR_USER_ID, entity.getUserId());

	    return AppConstants.USER_LOGIN_SUCCESS; 
	}

	 
	
	@Override
	public boolean unlockAccount(UnlockForm form)
	{
		UserDtls entity = userDtlsRepo.findByEmail(form.getEmail());
		if(entity.getPassword().equals(form.getTempPwd()))
		{
			entity.setPassword(form.getNewPwd());
			entity.setAccountStatus(AppConstants.STR_UNLOCKED);
			userDtlsRepo.save(entity);
			return true;
		}else {
			return false;
		}
	}
	
	
	@Override
	public boolean signUp(SignUpForm form)
	{
		UserDtls userEmail = userDtlsRepo.findByEmail(form.getEmail());
		
		if(userEmail!=null)
		{
			return false;
		}
		
		//Copy data from binding obj to entity obj
		UserDtls entity = new UserDtls();
		BeanUtils.copyProperties(form, entity);
		
		
		//generate random password.
		String tempPwd=PwdUtils.generarateRandomPwd();
		entity.setPassword(tempPwd);
		
		// set account status locked.
		
		entity.setAccountStatus(AppConstants.STR_LOCKED);
		
		// Insert Record.    
		
		userDtlsRepo.save(entity);
		
		// Send email to unlock the account
        String to = form.getEmail();
        String subject = AppConstants.UNLOCK_EMAIL_SUBJECT;
        StringBuilder body = new StringBuilder();
        body.append("<h1>Use the temporary password below to unlock your account</h1>")
            .append("<p>Temporary Password: <strong>").append(tempPwd).append("</strong></p>")
            .append("<br/>")
            .append("<a href=\"http://localhost:8080/unlock?email=")
            .append(to)
            .append("\">Click here to unlock your account</a>");

        emailUtils.sendEmail(to, subject, body.toString());
		return true;
	}
	
	
	@Override
	public boolean forgotPwd(String email)
	{
		// check for record.
		UserDtls entity = userDtlsRepo.findByEmail(email);
		 
		//if record not there then error
		if(entity==null)
		{
			return false;
		}
		 
		//if record available then send password to mail.
		 String Subject=AppConstants.RECOVER_SUBJECT_PASSWORD;
		 String body="Your Pwd::" + entity.getPassword();
		 emailUtils.sendEmail(email, Subject, body);
		 
		return true;
	}
	
}
