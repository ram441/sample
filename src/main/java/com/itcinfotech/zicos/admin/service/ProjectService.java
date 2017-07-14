package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Projects;

public interface ProjectService {
	public  Projects findProjectById(Long id) ;
	public  Projects getProjectByName(String roleName);
	public  List<Projects> findAllProjects();	
	public Projects saveProject(Projects projects);
	public Projects updateProject(Projects project);
	
	public List<Projects> loadProjectsByBusinessUnit(Long buId);
	public List<Projects> fetchAllProjects();
}
