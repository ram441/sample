package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DeployService;
import com.itcinfotech.zicos.sql.model.Deploy;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.repository.DeployRepository;
import com.itcinfotech.zicos.sql.repository.JobsRepository;

@Service
public class DeployServiceImpl implements DeployService{

	@Autowired
	private DeployRepository deployRepository;

	@Autowired
	private JobsRepository jobsRepository;
	
	@Override
	public List<Deploy> loadAllDeploy() {
		// TODO Auto-generated method stub
		return deployRepository.findAll();
	}

	@Override
	public Deploy findDeployByJobId(Integer jobId) {
		Long jobLong = Long.getLong(jobId.toString());
		Jobs job = jobsRepository.findOne(jobLong);
		return deployRepository.findByJobId(job);
	}

	@Override
	public Deploy findDeployByJobName(String jobName) {
		Jobs job = jobsRepository.findByJobName(jobName);
		return deployRepository.findByJobId(job);
	}

	@Override
	public Deploy saveDeploy(Deploy deploy) {
		// TODO Auto-generated method stub
		return deployRepository.save(deploy);
	}

	@Override
	public Deploy updateDeploy(Deploy deploy) {
		// TODO Auto-generated method stub
		return deployRepository.save(deploy);
	}
	
	
}
