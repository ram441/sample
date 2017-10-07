package com.itcinfotech.pbb.admin.service;

import java.util.List;

import com.itcinfotech.pbb.sql.model.Role;

public interface RoleService {
	public  Role findRoleById(Long id) ;
//	public  Role getRole(String roleName);
	public  List<Role> findAllRole();	
	public Role saveRole(Role role);
	public Role updateRole(Role role);
}
