package com.vaibhav.services;

import java.util.List;

import com.vaibhav.binding.DashBoardResponse;
import com.vaibhav.binding.EnquiryFilter;
import com.vaibhav.binding.EnquiryForm;
import com.vaibhav.entites.StudentEnq;

public interface EnquiryService
{
   	
	public DashBoardResponse getDashboardData(Integer userId);
	
	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public boolean saveEnquriry(EnquiryForm form);
	
	public List<StudentEnq> getEnquiries();
	
	public List<StudentEnq> getFilterEnqs(Integer userID, EnquiryFilter filter);
	
//	public String upsertEnqiry(EnquiryForm form);
//	
//	public List<StudentEnq> getFilterEnqs(Integer userID, 
//			EnquiryFilter filter);
//	
//	public EnquiryForm getEnquiry(Integer enqId); // Method for Edit Enquiry(Edit Button).

}
  