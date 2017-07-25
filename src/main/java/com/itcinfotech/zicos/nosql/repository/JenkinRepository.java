package com.itcinfotech.zicos.nosql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itcinfotech.zicos.nosql.model.Jenkin;

public interface JenkinRepository extends MongoRepository<Jenkin, String> {

	Jenkin findByBuildName(String buildName);

	List<Jenkin> findByProjectName(String projectName);

	List<Jenkin> findByJob(String jobName);

	@Query(value = "{'job' : ?0 }", count = true)
	Integer getTotalBuildCount(@Param("jobName") String jobName);
	
	@Query(value = "{'job' : ?0 , 'result' : 'SUCCESS'}", count = true)
	Integer getSuccessBuildCount(@Param("jobName") String jobName);
	
	@Query(value = "{'job' : ?0 , 'result' : 'FAILURE'}", count = true)
	Integer getFailedBuildCount(@Param("jobName") String jobName);
	
	@Query(value = "{'job' : ?0 , 'result' : 'ABORTED'}", count = true)
	Integer getAbortedBuildCount(@Param("jobName") String jobName);

}
