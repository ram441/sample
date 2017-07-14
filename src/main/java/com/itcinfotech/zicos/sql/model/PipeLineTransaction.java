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
@Table(name = "pipeline_transaction")
public class PipeLineTransaction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pipelineTransId;
	private PipeLine pipelineId;
	private Jobs jobId;
	private String isAssigned;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "pipeline_trans_id", unique = true, nullable = false)
	public Long getPipelineTransId() {
		return pipelineTransId;
	}
	public void setPipelineTransId(Long pipelineTransId) {
		this.pipelineTransId = pipelineTransId;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pipeline_id")
	public PipeLine getPipelineId() {
		return pipelineId;
	}
	public void setPipelineId(PipeLine pipelineId) {
		this.pipelineId = pipelineId;
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
	@Column(name = "is_assigned")
	public String isAssigned() {
		return isAssigned;
	}
	public void setAssigned(String isAssigned) {
		this.isAssigned = isAssigned;
	}
}
