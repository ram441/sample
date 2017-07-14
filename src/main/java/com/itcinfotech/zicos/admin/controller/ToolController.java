package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.ToolService;
import com.itcinfotech.zicos.sql.model.Tools;

@RestController
public class ToolController {

	@Autowired
	private ToolService toolService;
	@RequestMapping(value="/tool/{id}", method =RequestMethod.GET)
	public  Tools findToolById(@PathVariable("id") Long id) {
		return toolService.findToolById( id);
	}
	@RequestMapping(value="/tool/{toolName}", method =RequestMethod.GET)
	public  Tools getToolByName(@PathVariable("toolName") String toolName) {
		return toolService.getToolByName(toolName);
	}
	@RequestMapping(value="/tool", method =RequestMethod.GET)
	public  List<Tools> findAllTools() {
		return toolService.findAllTools();
	}
	@RequestMapping(value="/tool", method =RequestMethod.POST)
	public Tools saveTool(@RequestBody Tools toolPojo) {
		return toolService.saveTool(toolPojo);
	}
	@RequestMapping(value="/tool", method =RequestMethod.PUT)
	public Tools updateTool(@RequestBody Tools tool) {
		return toolService.updateTool(tool);
	}
}
