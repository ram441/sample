package com.itcinfotech.zicos.sql.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AbstractEntityAuditable<U,PK extends Serializable> implements Auditable<U,PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(name="created_by")
	private U createdBy;

	@ManyToOne
	@JoinColumn(name="last_modified_by")
	private U lastModifiedBy;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name="created_date")
	private DateTime createdDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name="last_modified_date")
	private DateTime lastModifiedDate;
	
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public U getCreatedBy() {
		// TODO Auto-generated method stub
		return this.createdBy;
	}

	@JsonIgnore
	@Override
	public DateTime getCreatedDate() {
		// TODO Auto-generated method stub
		return this.createdDate;
	}

	@JsonIgnore
	@Override
	public U getLastModifiedBy() {
		// TODO Auto-generated method stub
		return this.lastModifiedBy;
	}

	@JsonIgnore
	@Override
	public DateTime getLastModifiedDate() {
		// TODO Auto-generated method stub
		return this.lastModifiedDate;
	}

	@Override
	public void setCreatedBy(U createdBy) {

		this.createdBy=createdBy;
	}

	@Override
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
		
	}

	@Override
	public void setLastModifiedBy(U lastModifiedBy) {
		// TODO Auto-generated method stub
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		// TODO Auto-generated method stub
		this.lastModifiedDate = lastModifiedDate;
	}

	
	
}
