package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.PipeLine;
import com.itcinfotech.zicos.sql.model.Projects;

public interface PipeLineRepository extends JpaRepository<PipeLine, Long> {

	PipeLine findByProjectId(Projects project);

}
