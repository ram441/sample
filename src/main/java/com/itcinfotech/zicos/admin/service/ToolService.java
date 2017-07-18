package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Tools;

public interface ToolService {
	public  Tools findToolById(Long id) ;
	public  Tools getToolByName(String toolName);
	public  List<Tools> findAllTools();	
	public Tools saveTool(Tools toolPojo);
	public Tools updateTool(Tools tool);
	List<Tools> getToolsByProject(Long projectId);
}
