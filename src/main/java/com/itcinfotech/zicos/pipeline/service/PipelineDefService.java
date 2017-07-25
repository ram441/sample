package com.itcinfotech.zicos.pipeline.service;

import java.util.List;

import com.itcinfotech.zicos.pipeline.model.PipelineDef;

public interface PipelineDefService {

	PipelineDef updatePipelineDef(PipelineDef pipelineDef);

	PipelineDef findPipelineDefByProjectName(String projName);

	List<PipelineDef> loadAllPipelineDef(Long projId, Long envId);

}
