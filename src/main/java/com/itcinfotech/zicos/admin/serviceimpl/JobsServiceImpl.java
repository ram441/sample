package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.JobsService;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.JobsRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class JobsServiceImpl implements JobsService{

	@Autowired
	private JobsRepository jobsRepository;
	
	private ProjectsRepository projectRepository;
	@Override
	public List<Jobs> loadAllJobs() {
		// TODO Auto-generated method stub
		return jobsRepository.findAll();
	}

	@Override
	public List<Jobs> loadAJobsByProjectId(Long projectId) {
        Projects project = projectRepository.findOne(projectId);
		return jobsRepository.findByProjectId(project);
	}

	@Override
	public List<Jobs> loadAssignedJobToPipeByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jobs findJobsByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jobs findJobsByjobId(Long jobId) {
		// TODO Auto-generated method stub
		return jobsRepository.findOne(jobId);
	}

	@Override
	public Jobs updateJobs(Jobs jobs) {
		// TODO Auto-generated method stub
		return jobsRepository.save(jobs);
	}

	@Override
	public Jobs saveJobs(Jobs jenkinJobs) {
		// TODO Auto-generated method stub
		return jobsRepository.save(jenkinJobs);
	}
	
	
}
