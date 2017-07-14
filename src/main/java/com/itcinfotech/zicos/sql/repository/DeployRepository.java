package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Deploy;
import com.itcinfotech.zicos.sql.model.Jobs;

public interface DeployRepository extends JpaRepository<Deploy, Long> {

	Deploy findByJobId(Jobs job);

}
