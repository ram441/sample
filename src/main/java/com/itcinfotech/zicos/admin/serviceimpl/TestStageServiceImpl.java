package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.TestStageService;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.Test_Stage;
import com.itcinfotech.zicos.sql.repository.JobsRepository;
import com.itcinfotech.zicos.sql.repository.Test_StageRepository;

@Service
public class TestStageServiceImpl implements TestStageService  {

	@Autowired
	private Test_StageRepository test_StageRepository;
	@Autowired
	private JobsRepository jobsRepository;
	@Override
	public List<Test_Stage> loadAllDeploy() {
		// TODO Auto-generated method stub
		return test_StageRepository.findAll();
	}

	@Override
	public Test_Stage findTestStageByJobId(Long jobId) {

		Jobs job = jobsRepository.getOne(jobId);
		return test_StageRepository.findByJobId(job);
	}

	@Override
	public Test_Stage findTestStageByJobName(String jobName) {
		Jobs job = jobsRepository.findByJobName(jobName);
		return test_StageRepository.findByJobId(job);
	}

	@Override
	public Test_Stage saveTestStage(Test_Stage testStage) {
		// TODO Auto-generated method stub
		return test_StageRepository.save(testStage);
	}

	@Override
	public Test_Stage updateTestStage(Test_Stage testStage) {
		// TODO Auto-generated method stub
		return test_StageRepository.save(testStage);
	}
	
	
}
