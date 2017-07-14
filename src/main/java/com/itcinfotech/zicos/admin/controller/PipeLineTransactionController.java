package com.itcinfotech.zicos.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.PipeLineTransactionService;
import com.itcinfotech.zicos.sql.model.PipeLineTransaction;

@RestController
public class PipeLineTransactionController {

	@Autowired
	private PipeLineTransactionService pipeLineTransactionService;
	
	@RequestMapping(value="/pipelinetransaction", method =RequestMethod.POST)
	public PipeLineTransaction savePipeLineTransaction(@RequestBody PipeLineTransaction pipelineTransaction) {
		return pipeLineTransactionService.savePipeLineTransaction(pipelineTransaction);
	}
	@RequestMapping(value="/pipelinetransaction", method =RequestMethod.PUT)
	public PipeLineTransaction updatePipeLineTransaction(@RequestBody PipeLineTransaction pipelineTransaction) {
		return pipeLineTransactionService.updatePipeLineTransaction(pipelineTransaction);
	}
	@RequestMapping(value="/pipelinetransaction",params={"pipeId","jobId"}, method =RequestMethod.GET)
	public PipeLineTransaction getPipeLineTransactionByPipeAndJobId(@RequestParam("pipeId") Long pipeId,@RequestParam("jobId") Long jobId) {
		return pipeLineTransactionService.getPipeLineTransactionByPipeAndJobId(pipeId, jobId);
	}
}
