package com.vaibhav.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.vaibhav.entites.Course;
import com.vaibhav.entites.EnqStatus;
import com.vaibhav.repo.CourseRepository;
import com.vaibhav.repo.EnqStatusRepository;

@Component
public class DataLoader implements ApplicationRunner
{
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private EnqStatusRepository statusRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		courseRepo.deleteAll();
		
		Course c1= new Course();
		c1.setCourseId(1);
		c1.setCourseName("Java");
		
		Course c2= new Course();
		c2.setCourseId(2);
		c2.setCourseName("AWS");
		
		Course c3= new Course();
		c3.setCourseId(3);
		c3.setCourseName("SpringBoot");
		
		Course c4= new Course();
		c4.setCourseId(4);
		c4.setCourseName("Microservices");
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		statusRepo.deleteAll();
		
		EnqStatus s1= new EnqStatus();
		s1.setStatusId(1);
		s1.setStatusName("New");
		
		EnqStatus s2= new EnqStatus();
		s2.setStatusId(2);
		s2.setStatusName("Enrolled");
		
		EnqStatus s3= new EnqStatus();
		s3.setStatusId(3);
		s3.setStatusName("Lost");
		
		statusRepo.saveAll(Arrays.asList(s1,s2,s3));
		
		
	}
}
