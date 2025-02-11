package com.vaibhav.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController 
{

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
