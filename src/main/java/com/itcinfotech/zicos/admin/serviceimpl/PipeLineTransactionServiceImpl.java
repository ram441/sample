package com.itcinfotech.zicos.admin.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.PipeLineTransactionService;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.PipeLine;
import com.itcinfotech.zicos.sql.model.PipeLineTransaction;
import com.itcinfotech.zicos.sql.repository.JobsRepository;
import com.itcinfotech.zicos.sql.repository.PipeLineRepository;
import com.itcinfotech.zicos.sql.repository.PipeLineTransactionRepository;

@Service
public class PipeLineTransactionServiceImpl implements PipeLineTransactionService{

	@Autowired
	private PipeLineTransactionRepository pipeLineTransactionRepository;
	@Autowired
	private PipeLineRepository pipeLineRepository;
	@Autowired
	private JobsRepository jobsRepository;
	@Override
	public PipeLineTransaction savePipeLineTransaction(
			PipeLineTransaction pipelineTransaction) {
		// TODO Auto-generated method stub
		return pipeLineTransactionRepository.save(pipelineTransaction);
	}

	@Override
	public PipeLineTransaction updatePipeLineTransaction(
			PipeLineTransaction pipelineTransaction) {
		// TODO Auto-generated method stub
		return pipeLineTransactionRepository.save(pipelineTransaction);
	}

	@Override
	public PipeLineTransaction getPipeLineTransactionByPipeAndJobId(
			Long pipeId, Long jobId) {

		PipeLine pipeline = pipeLineRepository. findOne(pipeId);
		
		Jobs job = jobsRepository.findOne(jobId);
		return pipeLineTransactionRepository.findByPipelineIdAndJobId(pipeline,job);
	}
	
}
