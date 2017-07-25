package com.itcinfotech.zicos.pipeline.model;

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
@Table(name = "pipeline_txn")
public class PipeLineTxn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pipeline_txn_id")
	private Long pipelineTxnId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pipeline_def_id")
	private PipelineDef pipelineDef;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	private JobsPipeline jobsPipeline;
	
	@Column(name="is_assigned")
	private String isAssigned;
	
	@Column(name = "pipe_exec_id")
	private Long pipeExecId;
	
	@Column(name = "build_id")
	private Long buildId;

	
	public Long getPipelineTxnId() {
		return pipelineTxnId;
	}
	public void setPipelineTxnId(Long pipelineTxnId) {
		this.pipelineTxnId = pipelineTxnId;
	}
	
	
	public PipelineDef getPipelineDef() {
		return pipelineDef;
	}
	public void setPipelineDef(PipelineDef pipelineDef) {
		this.pipelineDef = pipelineDef;
	}
	
	public JobsPipeline getJobsPipeline() {
		return jobsPipeline;
	}
	public void setJobsPipeline(JobsPipeline jobsPipeline) {
		this.jobsPipeline = jobsPipeline;
	}
	
	public String getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(String isAssigned) {
		this.isAssigned = isAssigned;
	}
	
	public Long getPipeExecId() {
		return pipeExecId;
	}
	public void setPipeExecId(Long pipeExecId) {
		this.pipeExecId = pipeExecId;
	}
	
	public Long getBuildId() {
		return buildId;
	}
	public void setBuildId(Long buildId) {
		this.buildId = buildId;
	}
	@Override
	public String toString() {
		return "PipeLineTxn [pipelineTxnId=" + pipelineTxnId + ", pipelineDef="
				+ pipelineDef + ", jobsPipeline=" + jobsPipeline
				+ ", isAssigned=" + isAssigned + ", pipeExecId=" + pipeExecId
				+ ", buildId=" + buildId + "]";
	}
	
}
