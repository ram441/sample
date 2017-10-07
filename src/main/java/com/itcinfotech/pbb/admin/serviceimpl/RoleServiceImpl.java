package com.itcinfotech.pbb.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.pbb.admin.service.RoleService;
import com.itcinfotech.pbb.sql.model.Role;
import com.itcinfotech.pbb.sql.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role findRoleById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.getOne(id);
	}
/*
	@Override
	public Role getRole(String roleName) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(roleName);
	}*/

	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}
	
	
}
