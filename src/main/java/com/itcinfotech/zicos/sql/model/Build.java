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
@Table(name = "build")
public class Build implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long buildId;
	private Jobs job;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "build_id", unique = true, nullable = false)
	public Long getBuildId() {
		return buildId;
	}
	public void setBuildId(Long buildId) {
		this.buildId = buildId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	public Jobs getJob() {
		return job;
	}
	public void setJob(Jobs job) {
		this.job = job;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
