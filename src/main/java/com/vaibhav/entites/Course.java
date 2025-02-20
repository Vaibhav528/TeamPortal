package com.vaibhav.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="Course_tbl")
@Entity
public class Course
{
	@Id
	private Integer courseId;
	
	private String courseName;
	

}
