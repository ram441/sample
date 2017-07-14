package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.TestStageService;
import com.itcinfotech.zicos.sql.model.Test_Stage;

@RestController
public class TestStageController {

	@Autowired
	private TestStageService testStageService;
	
	public  List<Test_Stage> loadAllDeploy() {
		return testStageService.loadAllDeploy();
	}
	@RequestMapping(value="/teststage",params={"jobId"}, method =RequestMethod.GET)
	public Test_Stage findTestStageByJobId(@RequestParam("jobId") Long jobId) {
		return testStageService.findTestStageByJobId(jobId);
	}
	@RequestMapping(value="/teststage",params={"jobName"}, method =RequestMethod.GET)
	public Test_Stage findTestStageByJobName(@RequestParam("jobName") String jobName) {
		return testStageService.findTestStageByJobName(jobName);
	}
	@RequestMapping(value="/teststage", method =RequestMethod.POST)
	public Test_Stage saveTestStage(@RequestBody Test_Stage testStage) {
		return testStageService.saveTestStage(testStage);
	}
	@RequestMapping(value="/teststage", method =RequestMethod.PUT)
	public Test_Stage updateTestStage(@RequestBody Test_Stage testStage) {
		return testStageService.updateTestStage(testStage);
	}
}
