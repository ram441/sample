package com.itcinfotech.zicos.pipeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.pipeline.model.ViewProjects;

public interface ViewProjectsRepository extends JpaRepository<ViewProjects, Long> {

	ViewProjects findByProjName(String projName);

}
