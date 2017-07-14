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

@Entity
@Table(name = "dashboard_comp_role_xref")
public class DashBoardComponentRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long dashBoardComponentRoleId;
	private Role role;
	private DashBoardComponents dashBoardComponent;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "dashboard_comp_role_id", unique = true, nullable = false)
	public Long getDashBoardComponentRoleId() {
		return dashBoardComponentRoleId;
	}
	public void setDashBoardComponentRoleId(Long dashBoardComponentRoleId) {
		this.dashBoardComponentRoleId = dashBoardComponentRoleId;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dashboard_comp_id")
	public DashBoardComponents getDashBoardComponent() {
		return dashBoardComponent;
	}
	public void setDashBoardComponent(DashBoardComponents dashBoardComponent) {
		this.dashBoardComponent = dashBoardComponent;
	}
}
