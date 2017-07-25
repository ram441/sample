package com.itcinfotech.zicos.sql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;
import com.itcinfotech.zicos.sql.model.Projects;
@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

	public Projects findByProjectName(String projectName);

	public List<Projects> findByOrganisationBuConfig(OrganisationBuConfig buConfig);

	public List<Projects> findByActiveFlag(boolean b);

	public Projects findByProjectId(Long projectId);

}
