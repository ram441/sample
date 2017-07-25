package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.sql.model.Projects;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

	List<Environment> findByProject(Projects project);

}
