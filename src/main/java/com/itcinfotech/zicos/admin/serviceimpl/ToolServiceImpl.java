package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.ToolService;
import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.Tools;
import com.itcinfotech.zicos.sql.repository.DevopsToolRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.sql.repository.ToolsRepository;

@Service
public class ToolServiceImpl implements ToolService{

	@Autowired
	private ToolsRepository toolsRepository;
	
	@Autowired
	private DevopsToolRepository devopsToolRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Override
	public Tools findToolById(Long id) {
		// TODO Auto-generated method stub
		return toolsRepository.getOne(id);
	}

	@Override
	public Tools getToolByName(String toolName) {
		// TODO Auto-generated method stub
		return toolsRepository.findByToolName(toolName);
	}
	@Override
	public List<Tools> getToolsByProject(Long projectId) {
		Projects project = projectRepository.findOne(projectId);
		List<Tools> tools = toolsRepository.findAll();
		List<DevopsTool> devopsTool = devopsToolRepository.findByProject(project);
		//List<Tools> filteredList = new ArrayList<>();
		if(devopsTool!=null && devopsTool.size()>0) {
			for(DevopsTool devtool: devopsTool) {
				tools.remove(devtool.getTool());
			}
		}
		else {
			return tools;
		}
		return tools;
	}
	@Override
	public List<Tools> findAllTools() {
		// TODO Auto-generated method stub
		return toolsRepository.findAll();
	}

	@Override
	public Tools saveTool(Tools tool) {
		// TODO Auto-generated method stub
		return toolsRepository.save(tool);
	}

	@Override
	public Tools updateTool(Tools tool) {
		// TODO Auto-generated method stub
		return toolsRepository.save(tool);
	}
	
	
}
