package com.itcinfotech.pbb.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.itcinfotech.pbb.admin.service.LoginService;
import com.itcinfotech.pbb.sql.model.CustomUserDetails;

//@Service
public class LoginServiceImpl implements LoginService {
	
	
	 @Autowired
	 UserDetailsService loginDao;
	 
	public CustomUserDetails loadUserByUsername(String userName){
	
		return (CustomUserDetails) loginDao.loadUserByUsername(userName);
		
	}

	@Override
	public CustomUserDetails loadUserByMail(String email) {
		// TODO Auto-generated method stub
		return (CustomUserDetails) loginDao.loadUserByUsername(email);
	}

}
