package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.DashboardComponentService;
import com.itcinfotech.zicos.sql.model.DashBoardComponents;

@RestController
public class DashBoardComponentController {

	@Autowired
	private DashboardComponentService dashboardComponentService;
	
	@RequestMapping(value="/dashboardcomponent/{id}", method =RequestMethod.GET)
	public  DashBoardComponents findDashBoardComponentById(@PathVariable("id")Long id) {
		return dashboardComponentService.findDashBoardComponentById(id);
	}
	
	@RequestMapping(value="/dashboardcomponent",params = {"componentName"}, method =RequestMethod.GET)
	public  DashBoardComponents getDashBoardComponentByName(@RequestParam("componentName") String componentName){
		return dashboardComponentService.getDashBoardComponentByName(componentName);
	}
	@RequestMapping(value="/dashboardcomponent", method =RequestMethod.GET)
	public  List<DashBoardComponents> findAllDashBoardComponents() {
		return dashboardComponentService.findAllDashBoardComponents();
	}
	@RequestMapping(value="/dashboardcomponent", method =RequestMethod.POST)
	public DashBoardComponents saveDashBoardComponent(@RequestBody DashBoardComponents boardComponent) {
		return dashboardComponentService.saveDashBoardComponent(boardComponent);
	}
	@RequestMapping(value="/dashboardcomponent", method =RequestMethod.PUT)
	public DashBoardComponents updateDashBoardComponent(@RequestBody DashBoardComponents boardComponent) {
		return dashboardComponentService.updateDashBoardComponent(boardComponent);
	}
}
