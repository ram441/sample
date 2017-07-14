package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Jobs;

public interface JobsService {
	public  List<Jobs> loadAllJobs();
	public List<Jobs> loadAJobsByProjectId(Long projectId);
	public List<Jobs> loadAssignedJobToPipeByProjectId(Long projectId);
	public Jobs findJobsByProjectId(Long projectId);
//	public Jobs findJobsByProjectName(String projectName);
//	public boolean saveJobs(Jenkin jenkinJobs);
	public Jobs findJobsByjobId(Long jobId);
	public Jobs updateJobs(Jobs jobs);
	public Jobs saveJobs(Jobs jenkinJobs);
}
