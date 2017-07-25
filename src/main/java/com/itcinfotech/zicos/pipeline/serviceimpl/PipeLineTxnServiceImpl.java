package com.itcinfotech.zicos.pipeline.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.repository.JobsPipelineRepository;
import com.itcinfotech.zicos.pipeline.repository.PipeLineTxnRepository;
import com.itcinfotech.zicos.pipeline.repository.PipelineDefRepository;
import com.itcinfotech.zicos.pipeline.service.PipeLineTxnService;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;

@Service
public class PipeLineTxnServiceImpl implements PipeLineTxnService {

	@Autowired
	private PipeLineTxnRepository pipeLineTxnRepository;
	@Autowired
	private PipelineDefRepository pipelineDefRepository;
	@Autowired
	private JobsPipelineRepository jobsPipelineRepository;
	@Override
	public List<PipeLineTxn> savePipeLineTransactins(List<PipeLineTxn> pipelineTnxList) {
		// TODO Auto-generated method stub
		return pipeLineTxnRepository.save(pipelineTnxList);
	}

	@Override
	public List<PipeLineTxn> getPipeTxnByExecIdAndPipeDef(Long pipeExecId, Long pipelineDefId) {
		// TODO Auto-generated method stub
		return pipeLineTxnRepository.findByPipelineDefAndPipeExecId(pipelineDefId, pipeExecId);
	}

	@Override
	public void updatePipeLineTxn(List<PipeLineTxn> updatedPipeLineTxnLst) {
		pipeLineTxnRepository.save(updatedPipeLineTxnLst);
		
	}

	@Override
	public PipeLineTxn getPipeTxnByRunnigJobsAndExecIdAndDefId(String runningjob, Long pipeExecId, Long pipelineDefId) {
		PipelineDef pipelineDef = pipelineDefRepository.getOne(pipelineDefId);
		JobsPipeline jobsPipeLine = jobsPipelineRepository.findByjobName(runningjob);
		return pipeLineTxnRepository.findPipeLineTxnByPipeExecIdAndPepelineDef(jobsPipeLine, pipeExecId, pipelineDef);
	}

	@Override
	public void updatePipeLineTxn(PipeLineTxn pipeTxn) {
		pipeLineTxnRepository.save(pipeTxn);

	}

}
