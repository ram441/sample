package com.itcinfotech.zicos.pipeline.service;

import java.util.List;

import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;

public interface PipeLineTxnService {

	List<PipeLineTxn> savePipeLineTransactins(List<PipeLineTxn> pipelineTnxList);

	List<PipeLineTxn> getPipeTxnByExecIdAndPipeDef(Long pipeExecId, Long pipelineDefId);

	void updatePipeLineTxn(List<PipeLineTxn> updatedPipeLineTxnLst);

	PipeLineTxn getPipeTxnByRunnigJobsAndExecIdAndDefId(String runningjob, Long pipeExecId, Long pipelineDefId);

	void updatePipeLineTxn(PipeLineTxn pipeTxn);

}
