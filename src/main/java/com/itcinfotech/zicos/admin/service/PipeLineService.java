package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.PipeLine;

public interface PipeLineService{
	public  List<PipeLine> loadAllPipelines();
	public PipeLine findPipeLineByProjectId(Long projectId);
	public PipeLine savePipeLine(PipeLine pipeline);
	public PipeLine updatePipeLine(PipeLine pipeline);
}
