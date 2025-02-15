package com.vaibhav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaibhav.entites.UserDtls;

public interface UserDtlsRepository extends JpaRepository<UserDtls , Integer> 
{
	public UserDtls findByEmail(String email);
	
	 @Query("SELECT u FROM UserDtls u WHERE u.email = :email AND u.password = :password")
	   public UserDtls findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
 