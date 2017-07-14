package com.itcinfotech.zicos.nosql.serviceimpl;

import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.nosql.model.History;
import com.itcinfotech.zicos.nosql.model.Jira;
import com.itcinfotech.zicos.nosql.repository.JiraRepository;
import com.itcinfotech.zicos.nosql.service.JiraService;

@Service
public class JiraServiceImpl implements JiraService {

	private JiraRepository jiraRepository;
	@Override
	public Jira saveJiraData(Jira jira) {
		// TODO Auto-generated method stub
		return jiraRepository.save(jira);
	}

	@Override
	public Jira saveJiraHistory(History history) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public History findBySourceIdHistory(String sourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jira findByJiraKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jira update(History history) {
		// TODO Auto-generated method stub
		return null;
	}}

