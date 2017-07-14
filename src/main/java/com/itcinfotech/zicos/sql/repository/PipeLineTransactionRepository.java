package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.PipeLine;
import com.itcinfotech.zicos.sql.model.PipeLineTransaction;

public interface PipeLineTransactionRepository extends JpaRepository<PipeLineTransaction, Long> {

	PipeLineTransaction findByPipelineIdAndJobId(PipeLine pipeline, Jobs job);

}
