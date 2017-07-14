package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.EndUser;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.UserProjectConfig;

public interface UserProjectConfigRepository extends JpaRepository<UserProjectConfig, Long> {

	//UserProjectConfig findByEndUserAndProject(EndUser endUser, Projects project);

}
