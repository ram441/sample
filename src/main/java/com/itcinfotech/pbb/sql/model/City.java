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
@Table(name = "pbb_city")
public class City implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long cityId;
	private String cityName;
	private String createdBy;
	private Date createdDate;
	private Long isDelete;
	private Long isActive;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "city_id", unique = true, nullable = false)
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
