package com.itcinfotech.zicos.nosql.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.itcinfotech.zicos.nosql.service.LoginService;
import com.itcinfotech.zicos.sql.model.CustomUserDetails;

//@Service
public class LoginServiceImpl implements LoginService {
	
	
	 @Autowired
	 UserDetailsService loginDao;
	 
	public CustomUserDetails loadUserByUsername(String userName){
	
		return (CustomUserDetails) loginDao.loadUserByUsername(userName);
		
	}

	@Override
	public CustomUserDetails loadUserByMail(String userName) {
		// TODO Auto-generated method stub
		return (CustomUserDetails) loginDao.loadUserByUsername(userName);
	}

	@Override
	public CustomUserDetails loadGuestUserByMail(String userName) {
		// TODO Auto-generated method stub
		/*return loginDao.loadGuestUserByMail(userName);*/
		return null;
	};
	
	
	/*public List<CustomUser> loadUsersByUsername(String userName){
		 List<CustomUser> users=
		return 
	};*/

}
