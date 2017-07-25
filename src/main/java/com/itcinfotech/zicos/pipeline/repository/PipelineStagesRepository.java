package com.itcinfotech.zicos.pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.PipelineStages;

public interface PipelineStagesRepository extends JpaRepository<PipelineStages, Long> {

}
