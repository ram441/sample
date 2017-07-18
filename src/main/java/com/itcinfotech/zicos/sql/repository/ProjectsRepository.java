package com.itcinfotech.zicos.sql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;
import com.itcinfotech.zicos.sql.model.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {

	Projects findByProjectName(String projectName);

	List<Projects> findByOrganisationBuConfig(OrganisationBuConfig buConfig);

	List<Projects> findByActiveFlag(boolean b);

}
