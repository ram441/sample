package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Permission;

public interface PermissionService {
	public  Permission findPermissionById(Long id) ;
	public  Permission getPermission(String permissionName);
	public  List<Permission> findAllPermissions();	
	public Permission savePermission(Permission permission);
	public Permission updatePermission(Permission permission);
	public  List<Permission> loadPermission(Long userId,Long projectId);
	public  List<Permission> loadGuestUserPermission(Long guestUserId,Long projectId);
}
