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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "organisation")
public class Organisation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long organisationId;
	private String organisationName;
	private String organisationCode;
	private String creadtedBy;
	private String modifiedBy;
	private Date creadtedDate;
	private Date modifiedDate;
	private String contactEmail;
	private String contactName;
	private String contactMobile;
//	private List<Projects> projects = new ArrayList<Projects>(0);
	private Set<OrganisationBuConfig> organisationBuConfigs = new HashSet<OrganisationBuConfig>(0);
	
	private String imagePath;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "org_id", unique = true, nullable = false)
	public Long getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}
	@Column(name = "org_name")
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	
	@Column(name = "org_code")
	public String getOrganisationCode() {
		return organisationCode;
	}
	public void setOrganisationCode(String organisationCode) {
		this.organisationCode = organisationCode;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
	public List<Projects> getProjects() {
		return projects;
	}
	public void setProjects(List<Projects> projects) {
		this.projects = projects;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation")
	public List<OrganisationBuConfig> getOrganisationBuConfigs() {
		return organisationBuConfigs;
	}
	public void setOrganisationBuConfigs(
			List<OrganisationBuConfig> organisationBuConfigs) {
		this.organisationBuConfigs = organisationBuConfigs;
	}*/
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "organisation",cascade = CascadeType.PERSIST)
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,mappedBy="organisation")
	//@JoinColumn(name = "org_id")
	public Set<OrganisationBuConfig> getOrganisationBuConfigs() {
		return organisationBuConfigs;
	}
	public void setOrganisationBuConfigs(
			Set<OrganisationBuConfig> organisationBuConfigs) {
		this.organisationBuConfigs = organisationBuConfigs;
	}
	@Column(name = "contact_email")
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	@Column(name = "contact_name")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Column(name = "contact_mobile")
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * @return the imagePath
	 */
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
