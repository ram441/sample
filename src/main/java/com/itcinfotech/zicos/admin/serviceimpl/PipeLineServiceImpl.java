package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.PipeLineService;
import com.itcinfotech.zicos.sql.model.PipeLine;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.PipeLineRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class PipeLineServiceImpl implements PipeLineService{

	@Autowired
	private PipeLineRepository pipeLineRepository;
	
	@Autowired
	private ProjectsRepository projectsRepository;
	@Override
	public List<PipeLine> loadAllPipelines() {
		// TODO Auto-generated method stub
		return pipeLineRepository.findAll();
	}

	@Override
	public PipeLine findPipeLineByProjectId(Long projectId) {
		Projects project = projectsRepository.findOne(projectId);
		return pipeLineRepository.findByProjectId(project);
	}

	@Override
	public PipeLine savePipeLine(PipeLine pipeline) {
		// TODO Auto-generated method stub
		return pipeLineRepository.save(pipeline);
	}

	@Override
	public PipeLine updatePipeLine(PipeLine pipeline) {
		// TODO Auto-generated method stub
		return pipeLineRepository.save(pipeline);
	}
	
	
}
