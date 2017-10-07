package com.itcinfotech.pbb.sql.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itcinfotech.pbb.sql.repository.RoleRepository;


public class CustomUserDetails implements UserDetails {

	
	@Autowired
	RoleRepository roleRepo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Optional<User> user;
	
	
	public CustomUserDetails(Optional<User> user) {
		//super();
		this.user = user;
	}

	public Optional<User>  getUser() {
		return user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Long roleId = user.get().getRoleId();
		Role role = roleRepo.findOne(roleId);
		GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.get().getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.get().getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
