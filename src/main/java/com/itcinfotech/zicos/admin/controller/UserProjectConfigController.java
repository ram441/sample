package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.UserProjectService;
import com.itcinfotech.zicos.sql.model.UserProjectConfig;
import com.itcinfotech.zicos.sql.model.UserProjectPermission;

@RestController
public class UserProjectConfigController {

	@Autowired
	private UserProjectService userProjectService;
	
	@RequestMapping(value="/userprojectconfig/{id}", method =RequestMethod.GET)
	public  UserProjectConfig findUserProjectById(@PathVariable("id") Long id)  {
		return userProjectService.findUserProjectById(id);
	}
	@RequestMapping(value="/userprojectconfig",params={"userId","projectId"}, method =RequestMethod.GET)
	public  UserProjectConfig findUserProjectByUserIdAndProjectId(@RequestParam("userId") Long userId,@RequestParam("projectId") Long projectId) {
		return userProjectService.findUserProjectByUserIdAndProjectId(userId, projectId);
	}
	/*@RequestMapping(value="/userprojectconfig",params={"userProjectId","permissionId"}, method =RequestMethod.GET)
	public  UserProjectPermission findUserProjectPermissionByUserProjectAndPermissionId(@RequestParam("userProjectId") Long userProjectId,@RequestParam("permissionId") Long permissionId) {
		return userProjectService.findUserProjectPermissionByUserProjectAndPermissionId(userProjectId, permissionId);
	}*/
	@RequestMapping(value="/userprojectconfig", method =RequestMethod.GET)
	public  List<UserProjectConfig> findAllUserProjects() {
		return userProjectService.findAllUserProjects();
	}
	/*public  List<UserProjectConfig> findUserProjectByid(Long id) {
		return userProjectService.findUserProjectByid(id);
	}*/
	@RequestMapping(value="/userprojectconfig", method =RequestMethod.POST)
	public UserProjectConfig saveUserProject(@RequestBody UserProjectConfig userProject) {
		return userProjectService.saveUserProject(userProject);
	}
	@RequestMapping(value="/userprojectconfig", method =RequestMethod.PUT)
	public UserProjectConfig updateUserProject(@RequestBody UserProjectConfig userProject) {
		return userProjectService.updateUserProject(userProject);
	}
	//public UserProjectConfig saveUserProjectPermission(UserProjectPermission userProject);
}
