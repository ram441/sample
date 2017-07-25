package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.sql.model.Projects;

public interface JobsPipelineRepository extends JpaRepository<JobsPipeline, Long> {

	List<JobsPipeline> findByProjectAndPipelineDef(Projects project, PipelineDef pipeLineDef);

	List<JobsPipeline> findByProject(Projects project);

	List<JobsPipeline> findByProjectAndPipelineDefAndEnv(Projects project, PipelineDef pipeLineDef, Long envId);

	JobsPipeline findByIsFirstJob(boolean b);

	JobsPipeline findByjobName(String runningjob);

}
