package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "project")
public class Projects  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long projectId;
	//private Organisation organisation;
	private OrganisationBuConfig organisationBuConfig;
	private String projectCode;
	private String projectName;
	private String creadtedBy;
	private String modifiedBy;
	private Date creadtedDate;
	private Date modifiedDate;
	private String projectDesc;
	private boolean activeFlag;
//	private 
	
	/*private Set<UserProjectConfig> userProjectConfigs = new HashSet<UserProjectConfig>(0);
	private Set<Permission> userProjectPermissions = new HashSet<Permission>();*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "project_id", unique = true, nullable = false)
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}*/
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bu_id")
	public OrganisationBuConfig getOrganisationBuConfig() {
		return organisationBuConfig;
	}
	public void setOrganisationBuConfig(
			OrganisationBuConfig organisationBuConfig) {
		this.organisationBuConfig = organisationBuConfig;
	}
	@Column(name = "project_code")
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	@Column(name = "project_name")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Column(name = "creadted_by")
	public String getCreadtedBy() {
		return creadtedBy;
	}
	public void setCreadtedBy(String creadtedBy) {
		this.creadtedBy = creadtedBy;
	}
	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name = "creadted_date")
	public Date getCreadtedDate() {
		return creadtedDate;
	}
	public void setCreadtedDate(Date creadtedDate) {
		this.creadtedDate = creadtedDate;
	}
	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "devops_tool_xref", joinColumns = {@JoinColumn(name = "projectId")}, 
	inverseJoinColumns={@JoinColumn(name = "devop_tool_id")}
	inverseJoinColumns={@JoinColumn(name = "tool_id")}
	)
	public Set<DevopTool> getDevopTools() {
		return devopTools;
	}
	public void setDevopTools(Set<DevopTool> devopTools) {
		this.devopTools = devopTools;
	}*/
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@OneToMany(mappedBy = "project")
	public Set<UserProjectConfig> getUserProjectConfigs() {
		return userProjectConfigs;
	}
	public void setUserProjectConfigs(Set<UserProjectConfig> userProjectConfigs) {
		this.userProjectConfigs = userProjectConfigs;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "user_project_xref", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns={@JoinColumn(name = "project_id")})
	public Set<Permission> getUserProjectPermissions() {
		return userProjectPermissions;
	}
	public void setUserProjectPermissions(Set<Permission> userProjectPermissions) {
		this.userProjectPermissions = userProjectPermissions;
	}*/
	/**
	 * @return the projectDesc
	 */
	@Column(name = "project_desc")
	public String getProjectDesc() {
		return projectDesc;
	}
	/**
	 * @param projectDesc the projectDesc to set
	 */
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	@Column(name="is_active_flag")
	public boolean isActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	
}
