package com.vaibhav.services;

import java.util.List;

import com.vaibhav.binding.DashBoardResponse;
import com.vaibhav.binding.EnquiryFilter;
import com.vaibhav.binding.EnquiryForm;

public interface EnquiryService
{
   	
	public DashBoardResponse getDashboardData(Integer userId);
	
	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public boolean saveEnquriry(EnquiryForm form);
	
//	public String upsertEnqiry(EnquiryForm form);
//	
//	public List<EnquiryForm> getEnquries(Integer userID, 
//			EnquiryFilter criteria);
//	
//	public EnquiryForm getEnquiry(Integer enqId); // Method for Edit Enquiry(Edit Button).

}
 