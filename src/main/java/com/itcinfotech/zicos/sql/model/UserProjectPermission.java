package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Table(name = "user_project_permission_xref")
public class UserProjectPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userProjectPermissionId;
//	private UserProjectConfig userProject;
	private Long userProject;
	private Permission permission;
//	private Set<Permission> permissions = new HashSet<Permission>(0);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_project_permission_id", unique = true,nullable = false)
	public Long getUserProjectPermissionId() {
		return userProjectPermissionId;
	}
	public void setUserProjectPermissionId(Long userProjectPermissionId) {
		this.userProjectPermissionId = userProjectPermissionId;
	}
	
	@JsonIgnore
//	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "user_project_id")
	public Long getUserProject() {
		return userProject;
	}
	public void setUserProject(Long userProject) {
		this.userProject = userProject;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_id")
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	/*public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}*/
	
}
