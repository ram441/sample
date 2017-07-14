package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.InspectService;
import com.itcinfotech.zicos.sql.model.Inspect;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.repository.InspectRepository;
import com.itcinfotech.zicos.sql.repository.JobsRepository;

@Service
public class InspectServiceImpl implements InspectService {

	@Autowired
	private InspectRepository inspectRepository;
	@Autowired
	private JobsRepository jobRepository;
	@Override
	public List<Inspect> loadAllInspect() {
		// TODO Auto-generated method stub
		return inspectRepository.findAll();
	}

	@Override
	public Inspect findInspectByJobId(Long jobId) {
		Jobs job = jobRepository.findOne(jobId);
		return inspectRepository.findByJobId(job);
	}

	@Override
	public Inspect findInspectByJobName(String jobName) {
		Jobs job = jobRepository.findByJobName(jobName);
		return inspectRepository.findByJobId(job);
	}

	@Override
	public Inspect saveInspect(Inspect inspect) {
		// TODO Auto-generated method stub
		return inspectRepository.save(inspect);
	}

	@Override
	public Inspect updateInspect(Inspect inspect) {
		// TODO Auto-generated method stub
		return inspectRepository.save(inspect);
	}
	
	
}
