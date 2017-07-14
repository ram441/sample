package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Role;

public interface RoleService {
	public  Role findRoleById(Integer id) ;
	public  Role getRole(String roleName);
	public  List<Role> findAllRole();	
	public Role saveRole(Role role);
	public Role updateRole(Role role);
}
