package com.vaibhav.entites;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="Student_Enq_Tbl")
public class StudentEnq 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enquiryId;
	
	private String studentName;
	
	private String classMode;
	
	private Integer phoneNumber;
	
	private String courseName;
	
	private String enquiryStatus;
	
	@CreationTimestamp
	private Date createdDate;
	
	@UpdateTimestamp
	private Date updatedDate;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Defines foreign key
    private UserDtls user;  // Reference to User entity

}
