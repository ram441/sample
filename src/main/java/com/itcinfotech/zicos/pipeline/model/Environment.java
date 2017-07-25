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
@Table(name = "environment")
public class Environment  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "env_id", unique = true, nullable = false)
	private Long envId;
	
	@Column(name = "env_name")
	private String envName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Projects project;

	
	public Long getEnvId() {
		return envId;
	}
	public void setEnvId(Long envId) {
		this.envId = envId;
	}
	
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
