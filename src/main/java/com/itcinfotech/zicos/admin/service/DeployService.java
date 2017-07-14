package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Deploy;

public interface DeployService {
	public  List<Deploy> loadAllDeploy();
	public Deploy findDeployByJobId(Integer jobId);
	public Deploy findDeployByJobName(String jobName);
	public Deploy saveDeploy(Deploy deploy);
	public Deploy updateDeploy(Deploy deploy);
}
