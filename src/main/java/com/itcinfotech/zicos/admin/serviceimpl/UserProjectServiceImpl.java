package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.UserProjectService;
import com.itcinfotech.zicos.sql.model.EndUser;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.UserProjectConfig;
import com.itcinfotech.zicos.sql.model.UserProjectPermission;
import com.itcinfotech.zicos.sql.repository.EndUserRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.sql.repository.UserProjectConfigRepository;

@Service
public class UserProjectServiceImpl implements UserProjectService{

	@Autowired
	private UserProjectConfigRepository userProjectConfigRepository;
	
	@Autowired
	private EndUserRepository endUserRepository;
	@Autowired
	private ProjectsRepository projectsRepository;
	@Override
	public UserProjectConfig findUserProjectById(Long id) {
		// TODO Auto-generated method stub
		return userProjectConfigRepository.getOne(id);
	}

	@Override
	public UserProjectConfig findUserProjectByUserIdAndProjectId(Long userId,
			Long projectId) {
		EndUser endUser = endUserRepository.getOne(userId);
		Projects project = projectsRepository.getOne(projectId);
		//return userProjectConfigRepository.findByEndUserAndProject(endUser,project);
		return null;
	}

	/*@Override
	public UserProjectPermission findUserProjectPermissionByUserProjectAndPermissionId(
			Long userProjectId, Long permissionId) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<UserProjectConfig> findAllUserProjects() {
		// TODO Auto-generated method stub
		return userProjectConfigRepository.findAll();
	}

	@Override
	public List<UserProjectConfig> findUserProjectByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProjectConfig saveUserProject(UserProjectConfig userProject) {
		// TODO Auto-generated method stub
		return userProjectConfigRepository.save(userProject);
	}

	@Override
	public UserProjectConfig updateUserProject(UserProjectConfig userProject) {
		// TODO Auto-generated method stub
		return userProjectConfigRepository.save(userProject);
	}

	/*@Override
	public UserProjectConfig saveUserProjectPermission(
			UserProjectPermission userProject) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
