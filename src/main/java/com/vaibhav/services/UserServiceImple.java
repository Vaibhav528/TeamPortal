package com.vaibhav.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.binding.SignUpForm;
import com.vaibhav.entites.UserDtls;
import com.vaibhav.repo.UserDtlsRepository;
import com.vaibhav.utility.EmailUtils;
import com.vaibhav.utility.PwdUtils;

@Service
public class UserServiceImple implements UserService 
{
	@Autowired
	private UserDtlsRepository userDtlsRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
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
		
		entity.setAccountStatus("Locked");
		
		// Insert Record.
		
		userDtlsRepo.save(entity);
		
		//Send email to unlock the account.
		
		String to =form.getEmail();
		String subject="Unlock Your Account";
		StringBuffer body = new StringBuffer("");
		body.append("<h1> Use Below Temporary Password to unlock the Account</h1>");
		body.append("temporary Password:"+ tempPwd);
		body.append("<a href=\"localhost:8080/unlock?email="+ to+"\">Click Here to Unlock Your Account");
		emailUtils.sendEmail(to, subject, body.toString());
		return true;
	}

}
