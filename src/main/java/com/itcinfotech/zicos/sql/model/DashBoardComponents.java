package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dashboard_component")
public class DashBoardComponents implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dashBoardComponentId ; 
	private String dashBoardComponentName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "dashboard_comp_id", unique = true, nullable = false)
	public Long getDashBoardComponentId() {
		return dashBoardComponentId;
	}
	public void setDashBoardComponentId(Long dashBoardComponentId) {
		this.dashBoardComponentId = dashBoardComponentId;
	}
	
	@Column(name = "component_name")
	public String getDashBoardComponentName() {
		return dashBoardComponentName;
	}
	public void setDashBoardComponentName(String dashBoardComponentName) {
		this.dashBoardComponentName = dashBoardComponentName;
	}
	
}
