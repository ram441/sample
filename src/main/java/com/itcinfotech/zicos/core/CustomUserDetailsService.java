package com.itcinfotech.zicos.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.UserService;
import com.itcinfotech.zicos.sql.model.CustomUserDetails;
import com.itcinfotech.zicos.sql.model.EndUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		EndUser endUser = userService.findByEmailAndIsDisabled(username,false);
		if(endUser==null) {
			throw new UsernameNotFoundException("user"+ username+"not found");
		}
		return new CustomUserDetails(endUser);
	}

}
