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
@Table(name = "jobs")
public class Jobs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long jobId;
	private String jobName;
	private Projects projectId;
	private String isAssigned;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "job_id", unique = true, nullable = false)
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	@Column(name = "job_name")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	public Projects getProjectId() {
		return projectId;
	}
	public void setProjectId(Projects projectId) {
		this.projectId = projectId;
	}
	@Column(name = "is_assigned")
	public String getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(String isAssigned) {
		this.isAssigned = isAssigned;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*
//	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "contact_number")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "user_project_xref", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns={@JoinColumn(name = "project_id")})
	public Set<Projects> getProjects() {
		return projects;
	}

	public void setProjects(Set<Projects> projects) {
		this.projects = projects;
	}
	@Column(name = "image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@ManyToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable(name="end_user_role", 
                joinColumns={@JoinColumn(name="end_user_id")}, 
                inverseJoinColumns={@JoinColumn(name="role_id")})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
*/
	
}
