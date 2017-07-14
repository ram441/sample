package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Test_Stage;

public interface TestStageService{
	public  List<Test_Stage> loadAllDeploy();
	public Test_Stage findTestStageByJobId(Long jobId);
	public Test_Stage findTestStageByJobName(String jobName);
	public Test_Stage saveTestStage(Test_Stage testStage);
	public Test_Stage updateTestStage(Test_Stage testStage);
}
