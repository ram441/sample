package com.itcinfotech.pbb.admin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.itcinfotech.pbb.sql.model.CustomUserDetails;


public interface LoginService extends UserDetailsService {
	public CustomUserDetails loadUserByMail(String email);
}
