package com.itcinfotech.zicos.admin.service;

import com.itcinfotech.zicos.sql.model.PipeLineTransaction;

public interface PipeLineTransactionService {
	public PipeLineTransaction savePipeLineTransaction(PipeLineTransaction pipelineTransaction);
	public PipeLineTransaction updatePipeLineTransaction(PipeLineTransaction pipelineTransaction);
	public PipeLineTransaction getPipeLineTransactionByPipeAndJobId(Long pipeId, Long jobId);
}
