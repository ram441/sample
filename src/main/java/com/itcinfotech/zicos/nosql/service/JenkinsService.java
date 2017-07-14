package com.itcinfotech.zicos.nosql.service;

import java.util.List;

import com.itcinfotech.zicos.nosql.model.Jenkin;


public interface JenkinsService {
	public Jenkin saveJenkinsData(Jenkin jenkin);
	public Jenkin getJenkinData(String buildId,String jobName);
	public Jenkin getJenkinByBuildName(String buildName);
	public Jenkin update(Jenkin jenkin);
	public List<Jenkin> getJenkinAllCollectionData();
	public List<Jenkin> getJobNames();
	public List<Jenkin> getJobNamesByPorject(String projectName);
	public Integer getTotalBuildCount(String jobName);
	public Integer getSuccessBuildCount(String jobName);
	public Integer getFailedBuildCount(String jobName);
	public Integer getAbortedBuildCount(String jobName);
	public List<Jenkin> getTotalBuildNamesAndCount();
	public List<Jenkin> getSuccessBuildNamesAndCount();
	public List<Jenkin> getFailedBuildNamesAndCount();
	public List<Jenkin> getAbortedBuildNamesAndCount();
	public List<Jenkin> getLastBuildStatus(String jobName);
	public List<Jenkin> getIndividualBuildDetails(String jobName);
}
