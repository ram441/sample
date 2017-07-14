package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.PermissionService;
import com.itcinfotech.zicos.sql.model.Permission;

@RestController
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value="/permission/{id}", method =RequestMethod.GET)
	public  Permission findPermissionById(@PathVariable("id") Long id) {
		return permissionService.findPermissionById(id);
	}
	@RequestMapping(value="/permission/{permissionName}", method =RequestMethod.GET)
	public  Permission getPermission(@PathVariable("permissionName") String permissionName) {
		return permissionService.getPermission(permissionName);
	}
	@RequestMapping(value="/permission", method =RequestMethod.GET)
	public  List<Permission> findAllPermissions() {
		return permissionService.findAllPermissions();
				
	}
	@RequestMapping(value="/permission", method =RequestMethod.POST)
	public Permission savePermission(@RequestBody Permission permission) {
		return permissionService.savePermission(permission);
	}
	@RequestMapping(value="/permission", method =RequestMethod.PUT)
	public Permission updatePermission(@RequestBody Permission permission) {
		return permissionService.updatePermission(permission);
	}
	@RequestMapping(value="/permission",params={"userId","projectId"}, method =RequestMethod.GET)
	public  List<Permission> loadPermission(@RequestParam("userId") Long userId, @RequestParam("projectId") Long projectId) {
		return permissionService.loadPermission(userId, projectId);
	}
	@RequestMapping(value="/permission",params={"guestUserId","projectId"}, method =RequestMethod.GET)
	public  List<Permission> loadGuestUserPermission(@RequestParam("guestUserId") Long guestUserId,@RequestParam("projectId")Long projectId) {
		return permissionService.loadGuestUserPermission(guestUserId, projectId);
	}
}
