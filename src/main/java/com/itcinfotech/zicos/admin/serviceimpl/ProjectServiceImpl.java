package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.ProjectService;
import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.OrganisationBuConfigRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectsRepository projectsRepository;
	@Autowired
	private OrganisationBuConfigRepository organisationBuConfigRepository;
	@Override
	public Projects findProjectById(Long id) {
		// TODO Auto-generated method stub
		return projectsRepository.findOne(id);
	}

	@Override
	public Projects getProjectByName(String projectName) {
		// TODO Auto-generated method stub
		return projectsRepository.findByProjectName(projectName);
	}

	@Override
	public List<Projects> findAllProjects() {
		// TODO Auto-generated method stub
		return projectsRepository.findByActiveFlag(true);
	}

	@Override
	public Projects saveProject(Projects projects) {
		// TODO Auto-generated method stub
		return projectsRepository.save(projects);
	}

	@Override
	public Projects updateProject(Projects project) {
		// TODO Auto-generated method stub
		return projectsRepository.save(project);
	}

	@Override
	public List<Projects> loadProjectsByBusinessUnit(Long buId) {
		OrganisationBuConfig buConfig = organisationBuConfigRepository.findOne(buId);
		return projectsRepository.findByOrganisationBuConfig(buConfig);
	}

	@Override
	public List<Projects> fetchAllProjects() {
		return projectsRepository.findByActiveFlag(true);
	}
	
	
}
