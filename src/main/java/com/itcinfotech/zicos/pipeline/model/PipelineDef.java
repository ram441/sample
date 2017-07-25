package com.itcinfotech.zicos.pipeline.model;

import java.io.Serializable;

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
import com.itcinfotech.zicos.sql.model.Projects;

@Entity
@Table(name = "pipeline_def")
public class PipelineDef  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pipeline_def_id")
	private Long pipelineDefId;
	
	@Column(name = "pipeline_name")
	private String pipelineName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Projects projects;
	
	@Column(name = "pipe_exec_id")
	private Long pipeExecId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "env_id")
	private Environment environment;
	
	@Column(name = "current_status")
	private String currentStatus;
	
	
	public Long getPipelineDefId() {
		return pipelineDefId;
	}
	public void setPipelineDefId(Long pipelineDefId) {
		this.pipelineDefId = pipelineDefId;
	}
	
	public String getPipelineName() {
		return pipelineName;
	}
	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}
	
	
	public Projects getProjects() {
		return projects;
	}
	public void setProjects(Projects projects) {
		this.projects = projects;
	}
	
	
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public Long getPipeExecId() {
		return pipeExecId;
	}
	public void setPipeExecId(Long pipeExecId) {
		this.pipeExecId = pipeExecId;
	}
	
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
}
