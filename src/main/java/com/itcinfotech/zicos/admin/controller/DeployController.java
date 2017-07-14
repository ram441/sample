package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.DeployService;
import com.itcinfotech.zicos.sql.model.Deploy;

@RestController
public class DeployController {

	@Autowired
	private DeployService deployService;
	@RequestMapping(value="/deploy", method =RequestMethod.GET)
	public  List<Deploy> loadAllDeploy() {
		return deployService.loadAllDeploy();
	}
	@RequestMapping(value="/deploy",params = {"jobId"}, method =RequestMethod.GET)
	public Deploy findDeployByJobId(@RequestParam("jobId") Integer jobId) {
		return deployService.findDeployByJobId(jobId);
	}
	@RequestMapping(value="/deploy",params = {"jobName"}, method =RequestMethod.GET)
	public Deploy findDeployByJobName(@RequestParam("jobName") String jobName) {
		return deployService.findDeployByJobName(jobName);
	}
	@RequestMapping(value="/deploy", method =RequestMethod.POST)
	public Deploy saveDeploy(@RequestBody Deploy deploy) {
		return deployService.saveDeploy(deploy);
	}
	@RequestMapping(value="/deploy", method =RequestMethod.PUT)
	public Deploy updateDeploy(@RequestBody Deploy deploy) {
		return deployService.updateDeploy(deploy);
	}
}
