package com.itcinfotech.zicos.nosql.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.itcinfotech.zicos.sql.model.CustomUserDetails;


public interface LoginService extends UserDetailsService {
	public CustomUserDetails loadUserByUsername(String userName);
	public CustomUserDetails loadUserByMail(String userName);
	public CustomUserDetails loadGuestUserByMail(String userName);
}
