package com.itcinfotech.zicos.pipeline.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.model.ViewProjects;
import com.itcinfotech.zicos.pipeline.repository.EnvironmentRepository;
import com.itcinfotech.zicos.pipeline.repository.PipelineDefRepository;
import com.itcinfotech.zicos.pipeline.repository.ViewProjectsRepository;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;

@Service
public class PipelineDefServiceImpl implements PipelineDefService {

	@Autowired
	private PipelineDefRepository pipelineDefRepository;
	@Autowired
	private ViewProjectsRepository viewProjectRepository;
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
		ViewProjects project = viewProjectRepository.findByProjName(projName);
		return pipelineDefRepository.findByProjects(project);
	}

	@Override
	public List<PipelineDef> loadAllPipelineDef(Long projId, Long envId) {
		ViewProjects project = viewProjectRepository.getOne(projId);
		Environment environment = environmentRepository.getOne(envId);
		return pipelineDefRepository.findByProjectsAndEnvironment(project,environment);
	}

}
