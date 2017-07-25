package com.itcinfotech.zicos.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.DevopToolService;
import com.itcinfotech.zicos.admin.service.DevopsToolMapService;

import com.itcinfotech.zicos.sql.model.DevopTool;
import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.utils.Constants;

@RestController
public class DevopsToolController {
	
	@Autowired
	private DevopsToolMapService devopsToolMapService;
	@Autowired
	private DevopToolService devopToolService;
	
	@RequestMapping(value="/devopstool/{id}", method =RequestMethod.GET)
	public  DevopsTool findDevopsToolById(@PathVariable("id") Long id)  {
		return devopsToolMapService.findDevopsToolById(id);
	}
	@RequestMapping(value="/devopstool", method =RequestMethod.GET)
	public  List<DevopsTool> findAllDevopsTools() {
		return devopsToolMapService.findAllDevopsTools();
	}
	/*public  List<DevopsTool> findDevopsToolByid(Long id) {
		return devopsToolMapService.findDevopsToolByid(id);
	}*/
	@RequestMapping(value="/devopstool", method =RequestMethod.POST)
	public DevopsTool saveDevopsTool(@RequestBody DevopsTool devopsTool) {
		return devopsToolMapService.saveDevopsTool(devopsTool);
	}
	@RequestMapping(value="/devopstool", method =RequestMethod.PUT)
	public DevopsTool updateDevopsTool(@RequestBody DevopsTool devopsTool) {
		return devopsToolMapService.updateDevopsTool(devopsTool);
	}
	@RequestMapping(value="/devopstool",params={"projectId"}, method =RequestMethod.GET)
	public List<DevopsTool> findDevopsToolByProjectId(@RequestParam("projectId") Long projectId) {
		return devopsToolMapService.findDevopsToolByProjectId(projectId);
	}
	public DevopsTool saveDevopsToolDts(Long projectId, Long toolId,
			Long devopToolId) {
		return null;
	}
	
	@RequestMapping(value="/devoptool/{id}", method =RequestMethod.GET)
	public DevopTool getDevopTools(@PathVariable("id") Long id) {
		return devopToolService.findDevopToolById(id);
	}
	
	@RequestMapping(value="/devoptool", method =RequestMethod.GET)
	public List<DevopTool> getAllDevopTools() {
		return devopToolService.findAllDevopTools();
	}
	@RequestMapping(value="/connection/validation", method =RequestMethod.POST)
	public boolean validateConnectionConfig(@RequestBody DevopTool devopTool) {
		return devopToolService.validateConnectionConfig(devopTool);
	}
	
	@RequestMapping(value="/toolsConfig", method =RequestMethod.POST)
	public boolean saveToolsConfigForNewEnv(@RequestBody List<DevopsTool> devopsTools) {
		return devopsToolMapService.saveToolsConfigForNewEnv(devopsTools);
	}
	
	
	
	
	
}
