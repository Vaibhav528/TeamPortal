package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController 
{
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("/logout")
	public String Logout()
	{
		session.invalidate();
		return "index";
	}
	@GetMapping("/dashboard")
	public String dashBoardPage()
	{
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage()
	{
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiryPage()
	{
		return "view-enquires";
	}
}
