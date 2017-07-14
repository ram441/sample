package com.itcinfotech.zicos.nosql.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.nosql.model.Jenkin;
import com.itcinfotech.zicos.nosql.service.JenkinsService;

@Service
public class JenkinsServiceImpl implements JenkinsService{

	@Override
	public Jenkin saveJenkinsData(Jenkin jenkin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jenkin getJenkinData(String buildId, String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jenkin getJenkinByBuildName(String buildName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jenkin update(Jenkin jenkin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getJenkinAllCollectionData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getJobNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getJobNamesByPorject(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSuccessBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFailedBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAbortedBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getTotalBuildNamesAndCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getSuccessBuildNamesAndCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getFailedBuildNamesAndCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getAbortedBuildNamesAndCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getLastBuildStatus(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jenkin> getIndividualBuildDetails(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}