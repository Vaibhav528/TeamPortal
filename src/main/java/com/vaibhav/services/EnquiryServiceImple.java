package com.vaibhav.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.binding.DashBoardResponse;
import com.vaibhav.binding.EnquiryFilter;
import com.vaibhav.binding.EnquiryForm;
import com.vaibhav.constants.AppConstants;
import com.vaibhav.entites.*;
import com.vaibhav.repo.*;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImple implements EnquiryService 
{
	@Autowired
	private UserDtlsRepository userDtlsRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentEnqRepository enqRepo;
	
	@Autowired
	private EnqStatusRepository enqStatusRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public DashBoardResponse getDashboardData(Integer userId) 
	{
 //	    if (userId == null) {
//	        throw new IllegalArgumentException("User ID must not be null");
//	    }

	    DashBoardResponse response = new DashBoardResponse();
	    
	    Optional<UserDtls> optionalUser = userDtlsRepo.findById(userId);
	    
	    if (optionalUser.isPresent()) {
	        UserDtls userDtls = optionalUser.get();
	        List<StudentEnq> enquiries = userDtls.getEnquiries();
	        
	        Integer totalCnt = enquiries.size();
	        
	      Integer enrolledCnt= enquiries.stream()
	            .filter(e -> "Enrolled".equals(e.getEnquiryStatus()))
	            .collect(Collectors.toList()).size();
	       
	      Integer lostCount = enquiries.stream()
	            .filter(e -> "Lost".equals(e.getEnquiryStatus()))
	            .collect(Collectors.toList()).size();
	      
	      response.setTotalEnquriesCnt(totalCnt);
	      response.setEnrollesCnt(enrolledCnt);  // binding response class object
	      response.setLostCnt(lostCount);
	 
	    }
	    return response;
	}
	
	@Override
	public List<String> getCourseName()
	{   
		List<Course> findAll = courseRepo.findAll();
		
		List<String> names= new ArrayList();
		
		for(Course entity : findAll)
		{
			names.add(entity.getCourseName());
		}
		
		return names;
	}
	
	@Override
	public List<String> getEnqStatus()
	{
		List<EnqStatus> findAll = enqStatusRepo.findAll();
		
		List<String> statusList= new ArrayList();
		
		for(EnqStatus entity : findAll)
		{
			statusList.add(entity.getStatusName());
		}
		
		return statusList;		
		
	}
	
	@Override
	public boolean saveEnquriry(EnquiryForm form)
	{
		StudentEnq enqEntity =  new StudentEnq();
		BeanUtils.copyProperties(form,enqEntity);
		
		Integer userId= (Integer) session.getAttribute(AppConstants.STR_USER_ID);
		UserDtls userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		
		enqRepo.save(enqEntity);
		
		return true;
	}
	    
	@Override
	public List<StudentEnq> getEnquiries()
	{
		Integer userId=(Integer) session.getAttribute(AppConstants.STR_USER_ID);
		Optional<UserDtls> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent())
		{
			UserDtls userDtls = findById.get();
			List<StudentEnq> enquiries = userDtls.getEnquiries();
			return enquiries;
		}
		return null;
	}
	
	@Override
	public List<StudentEnq> getFilterEnqs(Integer userId, EnquiryFilter filter)
	{
		Optional<UserDtls> findById = userDtlsRepo.findById(userId);
		if(findById.isPresent())
		{
			UserDtls userDtls = findById.get();
			List<StudentEnq> enquiries = userDtls.getEnquiries();
			
			// Filter logic
			
			if(null!=filter.getCourseName() & !"".equals(filter.getCourseName()))
			{
				enquiries= enquiries.stream()
				.filter(e -> e.getCourseName() .equals(filter.getCourseName()))
				.collect(Collectors.toList());
			}
			
			if(null!=filter.getClassMode() & !"".equals(filter.getClassMode()))
			{
				enquiries= enquiries.stream()
				.filter(e -> e.getClassMode() .equals(filter.getClassMode()))
				.collect(Collectors.toList());
			}
			
			if(null!=filter.getEnquiryStatus() & !"".equals(filter.getEnquiryStatus()))
			{
				enquiries= enquiries.stream()
				.filter(e -> e.getEnquiryStatus() .equals(filter.getEnquiryStatus()))
				.collect(Collectors.toList());
			}
			
			return enquiries;
		}
		
		return null;
	}
	
	
	
	
}

		
