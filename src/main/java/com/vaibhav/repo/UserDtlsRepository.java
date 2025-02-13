package com.vaibhav.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.entites.UserDtls;

public interface UserDtlsRepository extends JpaRepository<UserDtls , Integer> 
{
	public UserDtls findByEmail(String email);

}
