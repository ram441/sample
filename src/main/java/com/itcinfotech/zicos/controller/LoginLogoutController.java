package com.itcinfotech.zicos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.sql.model.CustomUserDetails;
import com.itcinfotech.zicos.sql.model.EndUser;

@RestController
public class LoginLogoutController {

	@Value("${taas.mail.from}")
	private String mailFrom;
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public EndUser loginUser() {
		System.out.println(mailFrom+"+++++================================================");
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   
		if(user==null) {
	    	throw new UsernameNotFoundException("user not found");
	    }
	    return user.getEndUser();
	}
	
}
