package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Tools;

public interface ToolsRepository extends JpaRepository<Tools, Long> {

	Tools findByToolName(String toolName);

}
