package com.itcinfotech.zicos.sql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.Projects;

public interface JobsRepository extends JpaRepository<Jobs, Long> {

	Jobs findByJobName(String jobName);

	List<Jobs> findByProjectId(Projects project);

}
