package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.DashBoardComponents;

public interface DashBoardComponentsRepository extends JpaRepository<DashBoardComponents, Long> {

	DashBoardComponents findByDashBoardComponentName(String componentName);

}
