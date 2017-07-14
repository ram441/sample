package com.itcinfotech.zicos.nosql.service;

import com.itcinfotech.zicos.nosql.model.SonarQube;

public interface SonarQubeService {

	public SonarQube saveSonarData(SonarQube sonar);
	public SonarQube update(SonarQube sonar);
	public SonarQube findByProjectId(String projId);
}
