package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Build;

public interface BuildService {
	public  List<Build> loadAllBuilds();
	public Build findBuildByJobId(Long jobId);
	public Build findBuildByJobName(String jobName);
	public Build saveBuilds(Build builds);
	public Build updateBuilds(Build builds);
}
