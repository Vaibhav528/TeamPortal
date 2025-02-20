package com.vaibhav.entites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_Details")
public class UserDtls 
{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Auto-generate ID
	    private Integer userId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private Long phno;
	
	private String accountStatus;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<StudentEnq> enquiries;
	
	

}
