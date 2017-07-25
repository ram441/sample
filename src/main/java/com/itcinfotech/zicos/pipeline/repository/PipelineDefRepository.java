package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.model.ViewProjects;

public interface PipelineDefRepository extends JpaRepository<PipelineDef, Long> {

	public PipelineDef findByProjects(ViewProjects project);

	public List<PipelineDef> findByProjectsAndEnvironment(ViewProjects project, Environment environment);

}
