package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vaibhav.binding.SignUpForm;
import com.vaibhav.services.UserService;

@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form , Model model)
	{
		boolean status=userService.signUp(form);
		if(status) {
			model.addAttribute("Success Message", "Check Your mail");
			
		}else{
			model.addAttribute("errMsg", "choose Unqiue Email");
		}
		return "signUp";
	}
	
	
	
	
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUpPage(Model model)
	{
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unclockPage()
	{
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgotPwdPage()
	{
		return "forgotPwd";
	}
	
}
