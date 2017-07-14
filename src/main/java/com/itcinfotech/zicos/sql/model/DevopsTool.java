package com.itcinfotech.zicos.sql.model;

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

@Table
@Entity(name ="devops_tool_xref")
public class DevopsTool implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long devopsToolId;
	private Projects project;
	private Tools tool;
	private DevopTool devopTool;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "devops_tool_id", unique = true,nullable = false)
	public Long getDevopsToolId() {
		return devopsToolId;
	}
	public void setDevopsToolId(Long devopsToolId) {
		this.devopsToolId = devopsToolId;
	}
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tool_id")
	public Tools getTool() {
		return tool;
	}
	public void setTool(Tools tool) {
		this.tool = tool;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "devop_tool_id")
	public DevopTool getDevopTool() {
		return devopTool;
	}
	public void setDevopTool(DevopTool devopTool) {
		this.devopTool = devopTool;
	}
	
	
}
