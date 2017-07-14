package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DashBoardCompRoleService;
import com.itcinfotech.zicos.sql.model.DashBoardComponentRole;
import com.itcinfotech.zicos.sql.repository.DashBoardComponentRoleRepository;
import com.itcinfotech.zicos.sql.repository.RoleRepository;

@Service
public class DashBoardCompRoleServiceImpl implements DashBoardCompRoleService{

	@Autowired
	private DashBoardComponentRoleRepository dashBoardComponentRoleReposito;
	
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public DashBoardComponentRole findDashBoardComponentRoleById(Long id) {
		// TODO Auto-generated method stub
		return dashBoardComponentRoleReposito.findOne(id);
	}

	@Override
	public List<DashBoardComponentRole> findAllDashBoardComponentRoles() {
		// TODO Auto-generated method stub
		return dashBoardComponentRoleReposito.findAll();
	}

	@Override
	public List<DashBoardComponentRole> findDashBoardComponentRolesByid(Long id) {
		// TODO Auto-generated method stub
	//	return dashBoardComponentRoleReposito.findOne(id);
		return null;
	}

	@Override
	public DashBoardComponentRole saveDashBoardComponentRole(
			DashBoardComponentRole componentRole) {
		// TODO Auto-generated method stub
		return dashBoardComponentRoleReposito.save(componentRole);
	}

	@Override
	public DashBoardComponentRole updateDashBoardComponentRole(
			DashBoardComponentRole componentRole) {
		// TODO Auto-generated method stub
		return dashBoardComponentRoleReposito.save(componentRole);
	}
	
	
}
