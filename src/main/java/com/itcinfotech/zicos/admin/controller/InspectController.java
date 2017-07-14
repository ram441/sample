package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.InspectService;
import com.itcinfotech.zicos.sql.model.Inspect;

@RestController
public class InspectController {

	@Autowired
	private InspectService inspectService;
	
	@RequestMapping(value="/inspect", method =RequestMethod.GET)
	public  List<Inspect> loadAllInspect() {
		
		return inspectService.loadAllInspect();
	}
	@RequestMapping(value="/incpect",params={"jobId"}, method =RequestMethod.GET)
	public Inspect findInspectByJobId(@RequestParam("jobId") Long jobId) {
		return inspectService.findInspectByJobId(jobId);
	}
	@RequestMapping(value="/incpect",params={"jobName"}, method =RequestMethod.GET)
	public Inspect findInspectByJobName(@RequestParam("jobName") String jobName) {
		return inspectService.findInspectByJobName(jobName);
	}
	@RequestMapping(value="/inspect", method =RequestMethod.POST)
	public Inspect saveInspect(@RequestBody Inspect inspect) {
		return inspectService.saveInspect(inspect);
	}
	@RequestMapping(value="/inspect", method =RequestMethod.PUT)
	public Inspect updateInspect(@RequestBody Inspect inspect) {
		return inspectService.updateInspect(inspect);
	}
}
