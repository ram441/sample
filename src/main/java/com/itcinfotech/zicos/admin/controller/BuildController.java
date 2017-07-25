package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.BuildService;
import com.itcinfotech.zicos.sql.model.Build;

//@RestController
public class BuildController {

	@Autowired
	private BuildService buildService;
	
	@RequestMapping(value="/build", method =RequestMethod.GET)
	public  List<Build> loadAllBuilds() {
		
		return buildService.loadAllBuilds();
	}
	@RequestMapping(value="/build",params = {"jobId"}, method =RequestMethod.GET)
	public Build findBuildByJobId(@RequestParam("jobId") Long jobId) {
		return buildService.findBuildByJobId(jobId);
	}
	@RequestMapping(value="/build",params = {"jobName"}, method =RequestMethod.GET)
	public Build findBuildByJobName(@RequestParam("jobName") String jobName) {
		return buildService.findBuildByJobName(jobName);
	}
	@RequestMapping(value="/build", method =RequestMethod.POST)
	public Build saveBuilds(@RequestBody Build builds) {
		return buildService.saveBuilds(builds);
	}
	@RequestMapping(value="/build", method =RequestMethod.PUT)
	public Build updateBuilds(@RequestBody Build builds) {
		return buildService.updateBuilds(builds);
	}
}
