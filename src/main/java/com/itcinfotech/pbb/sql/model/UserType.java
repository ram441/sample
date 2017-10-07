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
@Table(name = "pbb_user_type")
public class UserType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userTypeId;
	private String userType;
	private String createdBy;
	private Date createdDate;
	private Long isDelete;
	private Long isActive;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_type_id", unique = true, nullable = false)
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
