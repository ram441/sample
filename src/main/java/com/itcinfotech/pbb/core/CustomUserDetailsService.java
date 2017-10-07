package com.itcinfotech.pbb.core;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itcinfotech.pbb.admin.service.UserService;
import com.itcinfotech.pbb.sql.model.CustomUserDetails;
import com.itcinfotech.pbb.sql.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Optional<User> endUser = userService.findUserByEmail(username);
		if(!endUser.isPresent()) {
			throw new UsernameNotFoundException("user"+ username+"not found");
		}
		return new CustomUserDetails(endUser);
	}

}
