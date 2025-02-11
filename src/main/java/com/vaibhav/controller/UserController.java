package com.vaibhav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController 
{
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUpPage()
	{
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
