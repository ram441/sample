package com.itcinfotech.zicos.pipeline.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.service.PipeLineService;

@RestController
@RequestMapping("/pipeline")
public class PipeLineController {
	
	static final Logger logger = LogManager.getLogger(PipeLineController.class.getName());
	@Autowired
	private PipeLineService pipeLineService;
	
	@RequestMapping(value="", method =RequestMethod.GET)
	public  List<JobsPipeline> loadAllPipelines() {
		return pipeLineService.loadAllPipelines();
	}
	@RequestMapping(value="",params={"projectId"}, method =RequestMethod.GET)
	public JobsPipeline findPipeLineByProjectId(@RequestParam("projectId") Long projectId) {
		return JobsPipeline.findPipeLineByProjectId(projectId);
	}
	@RequestMapping(value="", method =RequestMethod.POST)
	public JobsPipeline savePipeLine(@RequestBody JobsPipeline pipeline) {
		return pipeLineService.savePipeLine(pipeline);
	}
	@RequestMapping(value="", method =RequestMethod.PUT)
	public JobsPipeline updatePipeLine(@RequestBody JobsPipeline pipeline) {
		return pipeLineService.updatePipeLine(pipeline);
	}
	
	@RequestMapping(value="/jobspipeline", params= {"projectId","pipeDefId","envId"}, method=RequestMethod.GET)
	public List<JobsPipeline> getAllJobsPipeLineByProjectIdAndpieDefId(@RequestParam("projectId") Long projectId, @RequestParam("pipeDefId") Long pipeDefId, @RequestParam("envId") Long envId) {
		return pipeLineService.getAllJobsPipeLineByProjectIdAndpieDefId(projectId, pipeDefId,envId);
	}
	@RequestMapping(value="/pipelinetransactions", params= {"pipeDefId"}, method=RequestMethod.GET)
	public List<PipeLineTxn> getPipelineTransactionHistory(@RequestParam("pipeDefId") Long pipeDefId) {
		return pipeLineService.getPipelineTransactionHistory(pipeDefId);
	}
	//Pipeline Definition CRUD Start here
	@RequestMapping(value="/pipelinedef",method=RequestMethod.POST)
	public PipelineDef savePipeLineDef(@RequestBody PipelineDef pipelineDef) {
		return pipeLineService.savePipeLineDef(pipelineDef);
	}
	
	@RequestMapping(value="/executepipeline ", params = {"projectId","pipelineDefId","envId"}, method=RequestMethod.GET)
	public void runPipeLine(@RequestParam("projectid") Long projectId,@RequestParam("pipelineDefId") Long pipelineDefId, @RequestParam("envId") Long envId){
		
		pipeLineService.runPipeLine(projectId,pipelineDefId,envId);
	}
}
