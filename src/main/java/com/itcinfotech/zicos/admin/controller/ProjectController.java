package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.ProjectService;
import com.itcinfotech.zicos.sql.model.Projects;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/project/{id}", method =RequestMethod.GET)
	public  Projects findProjectById(@PathVariable("id")Long id) {
		return projectService.findProjectById(id);
	}
	@RequestMapping(value="/project", params={"projectName"}, method =RequestMethod.GET)
	public  Projects getProjectByName(@RequestParam("projectName") String projectName) {
		return projectService.getProjectByName(projectName);
	}
	@RequestMapping(value="/project", method =RequestMethod.GET)
	public  List<Projects> findAllProjects() {
		return projectService.fetchAllProjects();
	}
	@RequestMapping(value="/project", method =RequestMethod.POST)
	public Projects saveProject(@RequestBody Projects projects) {
		return projectService.saveProject(projects);
	}
	@RequestMapping(value="/project", method =RequestMethod.PUT)
	public Projects updateProject(@RequestBody Projects project) {
		return projectService.updateProject(project);
	}
	@RequestMapping(value="/project",params={"buId"}, method =RequestMethod.GET)
	public List<Projects> loadProjectsByBusinessUnit(@RequestParam("buId") Long buId) {
		return projectService.loadProjectsByBusinessUnit(buId);
	}
	/*public List<Projects> fetchAllProjects();*/
}
