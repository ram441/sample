package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Inspect;
import com.itcinfotech.zicos.sql.model.Jobs;

public interface InspectRepository extends JpaRepository<Inspect, Long> {

	Inspect findByJobId(Jobs job);

}
