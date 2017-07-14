package com.itcinfotech.zicos.nosql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.nosql.model.Jenkin;
import com.itcinfotech.zicos.nosql.service.JenkinsService;

@RestController
public class JenkinsController {

	@Autowired
	private JenkinsService jenkinsService;
	
	@RequestMapping(value="/jenkin", method =RequestMethod.POST)
	public Jenkin saveJenkinsData(@RequestBody Jenkin jenkin) {
		return jenkinsService.saveJenkinsData(jenkin);
	}
	@RequestMapping(value="/jenkin",params={"buildId","jobName"}, method =RequestMethod.GET)
	public Jenkin getJenkinData(@RequestParam("") String buildId,@RequestParam("") String jobName) {
		return jenkinsService.getJenkinData(buildId, jobName);
	}
	@RequestMapping(value="/jenkin",params={"buildName"}, method =RequestMethod.GET)
	public Jenkin getJenkinByBuildName(@RequestParam("buildName") String buildName) {
		return jenkinsService.getJenkinByBuildName(buildName);
	}
	@RequestMapping(value="/jenkin", method =RequestMethod.PUT)
	public Jenkin update(@RequestBody Jenkin jenkin) {
		return jenkinsService.update(jenkin);
	}
	@RequestMapping(value="/jenkin", method =RequestMethod.GET)
	public List<Jenkin> getJenkinAllCollectionData() {
		return jenkinsService.getJenkinAllCollectionData();
				
	}
	@RequestMapping(value="/jenkin/jobs", method =RequestMethod.GET)
	public List<Jenkin> getJobNames() {
		return jenkinsService.getJobNames();
	}
	@RequestMapping(value="/jenkin/jobs",params={"projectName"}, method =RequestMethod.GET)
	public List<Jenkin> getJobNamesByPorject(@RequestParam("projectName") String projectName) {
		return jenkinsService.getJobNamesByPorject(projectName);
	}
	@RequestMapping(value="/jenkin/build/count",params={"jobName"}, method =RequestMethod.GET)
	public Integer getTotalBuildCount(@RequestParam("jobName") String jobName) {
		return jenkinsService.getTotalBuildCount(jobName);
	}
	@RequestMapping(value="/jenkin/build/successcount",params={"jobName"}, method =RequestMethod.GET)
	public Integer getSuccessBuildCount(@RequestParam("jobName") String jobName) {
		return jenkinsService.getSuccessBuildCount(jobName);
	}
	@RequestMapping(value="/jenkin/build/failedcount",params={"jobName"}, method =RequestMethod.GET)
	public Integer getFailedBuildCount(@RequestParam("jobName") String jobName) {
		return jenkinsService.getFailedBuildCount(jobName);
	}
	@RequestMapping(value="/jenkin/build/abortedcount",params={"jobName"}, method =RequestMethod.GET)
	public Integer getAbortedBuildCount(@RequestParam("jobName") String jobName) {
		return jenkinsService.getAbortedBuildCount(jobName);
	}
	@RequestMapping(value="/jenkin/build", method =RequestMethod.GET)
	public List<Jenkin> getTotalBuildNamesAndCount() {
		return jenkinsService.getTotalBuildNamesAndCount();
	}
	@RequestMapping(value="/jenkin/build/success", method =RequestMethod.GET)
	public List<Jenkin> getSuccessBuildNamesAndCount() {
		return jenkinsService.getSuccessBuildNamesAndCount();
				
	}
	@RequestMapping(value="/jenkin/build/failed", method =RequestMethod.GET)
	public List<Jenkin> getFailedBuildNamesAndCount() {
		return jenkinsService.getFailedBuildNamesAndCount();
	}
	@RequestMapping(value="/jenkin/build/aborted", method =RequestMethod.GET)
	public List<Jenkin> getAbortedBuildNamesAndCount() {
		return jenkinsService.getAbortedBuildNamesAndCount();
	}
	@RequestMapping(value="/jenkin/build/laststatus",params={"jobName"}, method =RequestMethod.GET)
	public List<Jenkin> getLastBuildStatus(@RequestParam("jobName") String jobName) {
		return jenkinsService.getLastBuildStatus(jobName);
	}
	@RequestMapping(value="/jenkin/build/individual",params={"jobName"}, method =RequestMethod.GET)
	public List<Jenkin> getIndividualBuildDetails(@RequestParam("jobName") String jobName) {
		return jenkinsService.getIndividualBuildDetails(jobName);
	}
}
