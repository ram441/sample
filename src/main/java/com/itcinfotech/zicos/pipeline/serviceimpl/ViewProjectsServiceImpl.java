package com.itcinfotech.zicos.pipeline.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.pipeline.model.ViewProjects;
import com.itcinfotech.zicos.pipeline.repository.ViewProjectsRepository;
import com.itcinfotech.zicos.pipeline.service.ViewProjectsService;

@Service
public class ViewProjectsServiceImpl implements ViewProjectsService {

	@Autowired
	private ViewProjectsRepository viewProjectsRepository;
	@Override
	public ViewProjects findProjectById(Long projectId) {
		// TODO Auto-generated method stub
		return viewProjectsRepository.getOne(projectId);
	}

}
