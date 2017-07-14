package com.itcinfotech.zicos.nosql.service;

import com.itcinfotech.zicos.nosql.model.History;
import com.itcinfotech.zicos.nosql.model.Jira;

public interface JiraService {
	
	public Jira saveJiraData(Jira jira);
	public Jira saveJiraHistory(History history);
	public History findBySourceIdHistory(String sourceId);
	public Jira findByJiraKey(String key);
	public Jira update(History history);

}
