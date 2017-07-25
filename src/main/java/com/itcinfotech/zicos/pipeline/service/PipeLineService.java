package com.itcinfotech.zicos.pipeline.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;

public interface PipeLineService {
	public  List<JobsPipeline> loadAllPipelines();
	public JobsPipeline findPipeLineByProjectId(Long projectId);
	public JobsPipeline savePipeLine(JobsPipeline pipeline);
	public JobsPipeline updatePipeLine(JobsPipeline pipeline);
	
	public List<JobsPipeline> getAllJobsPipeLineByProjectIdAndpieDefId(Long projectId, Long pipeDefId, Long envId);

	public List<PipeLineTxn> getPipelineTransactionHistory(Long pipeDefId);
	
	public PipelineDef savePipeLineDef(PipelineDef pipelineDef);
	public boolean isPipeRunning(Long pipeDefId);
	
	public void runPipeLine(Long projectId,Long pipelineDefId,  Long envId);
	public List<JobsPipeline> findJobsByProjId(Long projectId);
}
