package com.itcinfotech.zicos.nosql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.nosql.model.Jenkin;
import com.itcinfotech.zicos.nosql.repository.JenkinRepository;
import com.itcinfotech.zicos.nosql.service.JenkinsService;

@Service
public class JenkinsServiceImpl implements JenkinsService{

	@Autowired
	private JenkinRepository jenkinRepository;
	@Override
	public Jenkin saveJenkinsData(Jenkin jenkin) {
		// TODO Auto-generated method stub
		return jenkinRepository.save(jenkin);
	}

	@Override
	public Jenkin getJenkinData(String buildId, String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jenkin getJenkinByBuildName(String buildName) {
		// TODO Auto-generated method stub
		return jenkinRepository.findByBuildName(buildName);
	}

	@Override
	public Jenkin update(Jenkin jenkin) {
		// TODO Auto-generated method stub
		return jenkinRepository.save(jenkin);
	}

	@Override
	public List<Jenkin> getJenkinAllCollectionData() {
		// TODO Auto-generated method stub
		return jenkinRepository.findAll();
	}

	@Override
	public List<Jenkin> getJobNames() {
		// TODO Auto-generated method stub
		return jenkinRepository.findAll();
	}

	@Override
	public List<Jenkin> getJobNamesByPorject(String projectName) {
		// TODO Auto-generated method stub
		return jenkinRepository.findByProjectName(projectName);
	}

	@Override
	public Integer getTotalBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return jenkinRepository.getTotalBuildCount(jobName);
	}

	@Override
	public Integer getSuccessBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return jenkinRepository.getSuccessBuildCount(jobName);
	}

	@Override
	public Integer getFailedBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return jenkinRepository.getFailedBuildCount(jobName);
	}

	@Override
	public Integer getAbortedBuildCount(String jobName) {
		// TODO Auto-generated method stub
		return jenkinRepository.getAbortedBuildCount(jobName);
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

		List<Jenkin> jenkinsMongoData= jenkinRepository.findByJob(jobName);
		return jenkinsMongoData;
	}

	@Override
	public List<Jenkin> getIndividualBuildDetails(String jobName) {
		return jenkinRepository.findByJob(jobName);
	}
	
	
}