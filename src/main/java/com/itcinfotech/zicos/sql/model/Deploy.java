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
@Table(name = "deploy")
public class Deploy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long deployId;
	private Jobs jobId;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "deploy_id", unique = true, nullable = false)
	public Long getDeployId() {
		return deployId;
	}
	public void setDeployId(Long deployId) {
		this.deployId = deployId;
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
