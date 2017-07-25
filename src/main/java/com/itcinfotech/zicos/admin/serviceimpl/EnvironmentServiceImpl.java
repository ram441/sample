package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.EnvironmentService;
import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.repository.EnvironmentRepository;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentRepository environmentRepository;
	
	private ProjectsRepository projectRepository;
	@Override
	public List<Environment> loadAllEnvironments(Long projId) {
		Projects project = projectRepository.getOne(projId);
		return environmentRepository.findByProject(project);
	}

}
