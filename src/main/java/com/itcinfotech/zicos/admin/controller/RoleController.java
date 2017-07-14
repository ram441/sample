package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.RoleService;
import com.itcinfotech.zicos.sql.model.Role;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	@RequestMapping(value="/role/{id}", method =RequestMethod.GET)
	public  Role findRoleById(@PathVariable("id") Integer id) {
		return roleService.findRoleById(id);
	}
	@RequestMapping(value="/role/{roleName}", method =RequestMethod.GET)
	public  Role getRole(@PathVariable("roleName") String roleName) {
		return roleService.getRole(roleName);
	}
	@RequestMapping(value="/role", method =RequestMethod.GET)
	public  List<Role> findAllRole() {
		return roleService.findAllRole();
				
	}
	@RequestMapping(value="/role", method =RequestMethod.POST)
	public Role saveRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}
	@RequestMapping(value="/role", method =RequestMethod.PUT)
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRole(role);
	}
}
