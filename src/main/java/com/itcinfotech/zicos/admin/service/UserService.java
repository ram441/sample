package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.EndUser;

public interface UserService {
	public  EndUser findUserById(Long id);
	public  EndUser getUserByDisplayName(String displayName);
	public  List<EndUser> findAllUsers();	
	public EndUser saveUser(EndUser user);
	public EndUser updateUser(EndUser user);
	public EndUser findByEmail(String username);
}
