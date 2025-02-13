package com.vaibhav.utility;
import org.apache.commons.lang3.RandomStringUtils;

public class PwdUtils
{

	public  static String generarateRandomPwd()
	{
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 9, characters );
		
		return pwd;
	}
	
}
