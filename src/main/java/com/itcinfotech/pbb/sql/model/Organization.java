package com.itcinfotech.pbb.sql.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pbb_organization")
public class Organization implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long organizationId;
	private String organizationName;
	private String createdBy;
	private Date createdDate;
	private Long isDelete;
	private Long isActive;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "organization_id", unique = true, nullable = false)
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	@Column(name = "organization_name")
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "is_delete")
	public Long getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name = "is_active")
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}
	
}
