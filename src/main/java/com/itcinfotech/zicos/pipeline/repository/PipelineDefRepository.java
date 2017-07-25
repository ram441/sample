package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.sql.model.Projects;

public interface PipelineDefRepository extends JpaRepository<PipelineDef, Long> {

	public PipelineDef findByProjects(Projects project);

	public List<PipelineDef> findByProjectsAndEnvironment(Projects project, Environment environment);

}
