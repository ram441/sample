package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.JobsService;
import com.itcinfotech.zicos.sql.model.Jobs;

@RestController
public class JobsController {

	@Autowired
	private JobsService jobsService;
	@RequestMapping(value="/jobs", method =RequestMethod.GET)
	public  List<Jobs> loadAllJobs() {
		return jobsService.loadAllJobs();
	}
	@RequestMapping(value="/jobs", params = {"projectId"}, method =RequestMethod.GET)
	public List<Jobs> loadAJobsByProjectId(@RequestParam("projectId") Long projectId) {
		return jobsService.loadAJobsByProjectId(projectId);
	}
	@RequestMapping(value="/jobs/pipeline", params = {"projectId"}, method =RequestMethod.GET)
	public List<Jobs> loadAssignedJobToPipeByProjectId(Long projectId) {
		return jobsService.loadAssignedJobToPipeByProjectId(projectId);
	}
	public Jobs findJobsByProjectId(Long projectId) {
		return jobsService.findJobsByProjectId(projectId);
	}
	@RequestMapping(value="/jobs/{jobId}", method =RequestMethod.GET)
	public Jobs findJobsByjobId(@PathVariable("jobId") Long jobId) {
		return jobsService.findJobsByjobId(jobId);
	}
	@RequestMapping(value="/jobs", method =RequestMethod.PUT)
	public Jobs updateJobs(@RequestBody Jobs jobs) {
		return jobsService.updateJobs(jobs);
	}
	@RequestMapping(value="/jobs", method =RequestMethod.POST)
	public Jobs saveJobs(@RequestBody Jobs jenkinJobs) {
		return jobsService.saveJobs(jenkinJobs);
	}
}
