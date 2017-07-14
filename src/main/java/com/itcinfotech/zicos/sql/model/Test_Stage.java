package com.itcinfotech.zicos.sql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_stage")
public class Test_Stage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int testStageId;
	private Jobs jobId;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "test_stage_id", unique = true, nullable = false)
	public int getTestStageId() {
		return testStageId;
	}
	public void setTestStageId(int testStageId) {
		this.testStageId = testStageId;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	public Jobs getJobId() {
		return jobId;
	}
	public void setJobId(Jobs jobId) {
		this.jobId = jobId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
