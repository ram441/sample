package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.PermissionService;
import com.itcinfotech.zicos.sql.model.Permission;
import com.itcinfotech.zicos.sql.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public Permission findPermissionById(Long id) {
		// TODO Auto-generated method stub
		return permissionRepository.findOne(id);
	}

	@Override
	public Permission getPermission(String permissionName) {
		// TODO Auto-generated method stub
		return permissionRepository.findByName(permissionName);
	}

	@Override
	public List<Permission> findAllPermissions() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}

	@Override
	public Permission savePermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionRepository.save(permission);
	}

	@Override
	public Permission updatePermission(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public List<Permission> loadPermission(Long userId, Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> loadGuestUserPermission(Long guestUserId,
			Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
