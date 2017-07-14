package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.PipeLineService;
import com.itcinfotech.zicos.sql.model.PipeLine;

@RestController
public class PipeLineController {

	@Autowired
	private PipeLineService pipeLineService;
	@RequestMapping(value="/pipeline", method =RequestMethod.GET)
	public  List<PipeLine> loadAllPipelines() {
		return pipeLineService.loadAllPipelines();
	}
	@RequestMapping(value="/pipeline",params={"projectId"}, method =RequestMethod.GET)
	public PipeLine findPipeLineByProjectId(@RequestParam("projectId") Long projectId) {
		return pipeLineService.findPipeLineByProjectId(projectId);
	}
	@RequestMapping(value="/pipeline", method =RequestMethod.POST)
	public PipeLine savePipeLine(@RequestBody PipeLine pipeline) {
		return pipeLineService.savePipeLine(pipeline);
	}
	@RequestMapping(value="/pipeline", method =RequestMethod.PUT)
	public PipeLine updatePipeLine(@RequestBody PipeLine pipeline) {
		return pipeLineService.updatePipeLine(pipeline);
	}
}
