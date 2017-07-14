package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.DashBoardCompRoleService;
import com.itcinfotech.zicos.sql.model.DashBoardComponentRole;

@RestController
public class DashBoardComponentRoleController {

	@Autowired
	private DashBoardCompRoleService dashBoardCompRoleService;
	
	@RequestMapping(value="/dashboardcomponentrole/{id}", method =RequestMethod.GET)
	public  DashBoardComponentRole findDashBoardComponentRoleById(@PathVariable Long id) {
		return dashBoardCompRoleService.findDashBoardComponentRoleById(id);
	}
	@RequestMapping(value="/dashboardcomponentrole", method =RequestMethod.GET)
	public  List<DashBoardComponentRole> findAllDashBoardComponentRoles() {
		return dashBoardCompRoleService.findAllDashBoardComponentRoles();
	}
	/*@RequestMapping(value="/dashboardcomponentrole/{id}", method =RequestMethod.GET)
	public  List<DashBoardComponentRole> findDashBoardComponentRolesByid(@PathVariable Long id) {
		return dashBoardCompRoleService.findDashBoardComponentRolesByid(id);
	}*/
	@RequestMapping(value="/dashboardcomponentrole", method =RequestMethod.POST)
	public DashBoardComponentRole saveDashBoardComponentRole(@RequestBody DashBoardComponentRole componentRole) {
		return dashBoardCompRoleService.saveDashBoardComponentRole(componentRole);
	}
	@RequestMapping(value="/dashboardcomponentrole", method =RequestMethod.PUT)
	public DashBoardComponentRole updateDashBoardComponentRole(@RequestBody DashBoardComponentRole componentRole) {
		return dashBoardCompRoleService.updateDashBoardComponentRole(componentRole);
	}
}
