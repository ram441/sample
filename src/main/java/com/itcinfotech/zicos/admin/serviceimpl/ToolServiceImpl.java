package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.ToolService;
import com.itcinfotech.zicos.sql.model.Tools;
import com.itcinfotech.zicos.sql.repository.ToolsRepository;

@Service
public class ToolServiceImpl implements ToolService{

	@Autowired
	private ToolsRepository toolsRepository;
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
