package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.DashBoardComponentRole;


public interface DashBoardCompRoleService {
	public  DashBoardComponentRole findDashBoardComponentRoleById(Long id) ;
	public  List<DashBoardComponentRole> findAllDashBoardComponentRoles();
	public  List<DashBoardComponentRole> findDashBoardComponentRolesByid(Long id);	
	public DashBoardComponentRole saveDashBoardComponentRole(DashBoardComponentRole componentRole);
	public DashBoardComponentRole updateDashBoardComponentRole(DashBoardComponentRole componentRole);
}
