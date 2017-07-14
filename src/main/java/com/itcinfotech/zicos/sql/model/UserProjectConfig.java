package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "user_project_xref")
@Entity
public class UserProjectConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userProjectId;
	private Projects project;
	private Set<Permission> permissions = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_project_id", unique = true, nullable = false)
	public Long getUserProjectId() {
		return userProjectId;
	}

	public void setUserProjectId(Long userProjectId) {
		this.userProjectId = userProjectId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "user_project_permission_xref", joinColumns = @JoinColumn(name = "user_project_id"),
	                   inverseJoinColumns = @JoinColumn(name = "permission_id"))
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


}
