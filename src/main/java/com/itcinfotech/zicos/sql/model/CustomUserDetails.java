package com.itcinfotech.zicos.sql.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EndUser endUser;
	
	
	public CustomUserDetails(EndUser endUser) {
		//super();
		this.endUser = endUser;
	}

	public EndUser getEndUser() {
		return endUser;
	}

	/*public void setEndUser(EndUser endUser) {
		this.endUser = endUser;
	}*/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		return new ArrayList<GrantedAuthority>(endUser.getRole().getRoleId());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return endUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return endUser.getEmail();
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
