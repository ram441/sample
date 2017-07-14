package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.BuildService;
import com.itcinfotech.zicos.sql.model.Build;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.repository.BuildRepository;
import com.itcinfotech.zicos.sql.repository.JobsRepository;

@Service
public class BuildServiceImpl implements BuildService  {

	@Autowired
	private BuildRepository buildRepository;
	@Autowired
	private JobsRepository jobRepository;
	@Override
	public List<Build> loadAllBuilds() {
		// TODO Auto-generated method stub
		return buildRepository.findAll();
	}

	@Override
	public Build findBuildByJobId(Long jobId) {
		// TODO Auto-generated method stub
		Jobs job = jobRepository.findOne(jobId);
		return buildRepository.findByJob(job);
	}

	@Override
	public Build findBuildByJobName(String jobName) {
		Jobs job = jobRepository.findByJobName(jobName);
		return buildRepository.findByJob(job);
	}

	@Override
	public Build saveBuilds(Build builds) {
		return buildRepository.save(builds);
	}

	@Override
	public Build updateBuilds(Build builds) {
		// TODO Auto-generated method stub
		return buildRepository.save(builds);
	}
	
	
}
