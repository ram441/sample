package com.itcinfotech.zicos.pipeline.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author 23038
 *
 */
@Entity
@Table(name = "view_projects")
public class ViewProjects implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long viewProjId;
	private String projName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "view_projects_id")
	public Long getViewProjId() {
		return viewProjId;
	}
	public void setViewProjId(Long viewProjId) {
		this.viewProjId = viewProjId;
	}
	@Column(name = "project_name")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
