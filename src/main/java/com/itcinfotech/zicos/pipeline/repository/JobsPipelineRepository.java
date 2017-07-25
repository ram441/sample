package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.model.ViewProjects;

public interface JobsPipelineRepository extends JpaRepository<JobsPipeline, Long> {

	List<JobsPipeline> findByProjectAndPipelineDef(ViewProjects project, PipelineDef pipeLineDef);

	List<JobsPipeline> findByProject(ViewProjects viewProject);

	List<JobsPipeline> findByProjectAndPipelineDefAndEnv(ViewProjects project, PipelineDef pipeLineDef, Long envId);

	JobsPipeline findByIsFirstJob(boolean b);

	JobsPipeline findByjobName(String runningjob);

}
