package com.itcinfotech.zicos.pipeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.model.ViewProjects;

public interface PipeLineTxnRepository extends JpaRepository<PipeLineTxn, Long> {

	List<PipeLineTxn> findByPipelineDef(PipelineDef pipeLineDef);

	List<PipeLineTxn> findByPipelineDefAndPipeExecId(Long pipelineDefId, Long pipeExecId);
	
	@Query("SELECT pt FROM PipeLineTxn pt WHERE pt.pipeExecId = :pipeExecId AND pt.pipelineDef= :pipeDef AND pt.jobsPipeline = :jobsPipeline")
	PipeLineTxn findPipeLineTxnByPipeExecIdAndPepelineDef(@Param("jobsPipeline") JobsPipeline jobsPipeline,@Param("pipeExecId") Long pipeExecId, @Param("pipeDef") PipelineDef pipeDef);


}
