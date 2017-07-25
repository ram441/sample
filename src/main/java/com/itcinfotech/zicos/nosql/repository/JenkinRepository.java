package com.itcinfotech.zicos.nosql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itcinfotech.zicos.nosql.model.Jenkin;

public interface JenkinRepository extends MongoRepository<Jenkin, String> {

	Jenkin findByBuildName(String buildName);

	List<Jenkin> findByProjectName(String projectName);

}
