package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.UserProjectConfig;
import com.itcinfotech.zicos.sql.model.UserProjectPermission;

public interface UserProjectService {
	public  UserProjectConfig findUserProjectById(Long id) ;
	public  UserProjectConfig findUserProjectByUserIdAndProjectId(Long userId, Long projectId);
//	public  UserProjectPermission findUserProjectPermissionByUserProjectAndPermissionId(Long userProjectId, Long permissionId);
	public  List<UserProjectConfig> findAllUserProjects();
	public  List<UserProjectConfig> findUserProjectByid(Long id);	
	public UserProjectConfig saveUserProject(UserProjectConfig userProject);
	public UserProjectConfig updateUserProject(UserProjectConfig userProject);
	//public UserProjectConfig saveUserProjectPermission(UserProjectPermission userProject);
}
