package com.vaibhav.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.binding.DashBoardResponse;
import com.vaibhav.binding.EnquiryForm;
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
		
		Integer userId= (Integer) session.getAttribute("userId");
		UserDtls userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);
		
		enqRepo.save(enqEntity);
		
		return true;
	}
	    
}

		
