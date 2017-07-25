package com.itcinfotech.zicos.sql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.sql.model.Projects;

@Transactional
public interface DevopsToolRepository extends JpaRepository<DevopsTool, Long> {

	List<DevopsTool> findByProject(Projects project);

}
