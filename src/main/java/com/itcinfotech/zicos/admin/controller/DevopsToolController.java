package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.DevopsToolMapService;
import com.itcinfotech.zicos.sql.model.DevopsTool;

@RestController
public class DevopsToolController {
	
	@Autowired
	private DevopsToolMapService devopsToolMapService;
	
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
}
