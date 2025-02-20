package com.vaibhav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vaibhav.binding.DashBoardResponse;
import com.vaibhav.binding.EnquiryForm;
import com.vaibhav.services.EnquiryService;


import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController 
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	private  EnquiryService enqService;
	
	@GetMapping("/logout")
	public String Logout()
	{
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard") // model to send data from contrller to UI.
	public String dashboard(HttpSession session, Model model) {

		Integer userId=(Integer) session.getAttribute("userId");
		
		DashBoardResponse dashboradData = 
				enqService.getDashboardData(userId);
		
		model.addAttribute("dashboradData",dashboradData);

	    return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiry( @ModelAttribute("formObj") EnquiryForm formObj , Model model )
	{
		System.out.println(formObj);
	
		//save data
		boolean status = enqService.saveEnquriry(formObj);
		if(status)
		{
			model.addAttribute("succMsg" , "Enquiry Added");
		}else
		{
			model.addAttribute("errMsg" , "Fail to add");
		}
		
		return "add-enquiry";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model)
	{
		// get coursess for dropdown
		
		List<String> courses = enqService.getCourseName();
		
		// getenq status for dropdown
		
		List<String> enqStatus = enqService.getEnqStatus();		
		
		// create binding class obj.
		
		EnquiryForm formObj = new EnquiryForm();
		
		//set data in model object.
		
		model.addAttribute("courseNames",courses);
		model.addAttribute("statusNames",enqStatus );
		model.addAttribute("formObj",formObj  );
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiryPage()
	{
		return "view-enquires";
	}
}
