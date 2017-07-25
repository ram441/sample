package com.itcinfotech.zicos.pipeline.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.repository.EnvironmentRepository;
import com.itcinfotech.zicos.pipeline.repository.PipelineDefRepository;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class PipelineDefServiceImpl implements PipelineDefService {

	@Autowired
	private PipelineDefRepository pipelineDefRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private EnvironmentRepository environmentRepository;
	@Override
	public PipelineDef updatePipelineDef(PipelineDef pipelineDef) {
		// TODO Auto-generated method stub
		return pipelineDefRepository.save(pipelineDef);
	}

	@Override
	public PipelineDef findPipelineDefByProjectName(String projName) {
		// TODO Auto-generated method stub
		Projects project = projectRepository.findByProjectName(projName);
		return pipelineDefRepository.findByProjects(project);
	}

	@Override
	public List<PipelineDef> loadAllPipelineDef(Long projId, Long envId) {
		Projects project = projectRepository.getOne(projId);
		Environment environment = environmentRepository.getOne(envId);
		return pipelineDefRepository.findByProjectsAndEnvironment(project,environment);
	}

}
