package com.vaibhav.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="enquriry_status_tbl")
public class EnqStatus 

{
	@Id
	private Integer statusId;
	private String statusName;
	
}
