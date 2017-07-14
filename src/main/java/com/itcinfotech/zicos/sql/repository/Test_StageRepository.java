package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.Test_Stage;

public interface Test_StageRepository extends JpaRepository<Test_Stage, Integer> {

	Test_Stage findByJobId(Jobs job);

}
