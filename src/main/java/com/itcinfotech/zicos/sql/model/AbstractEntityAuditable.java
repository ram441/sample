package com.itcinfotech.zicos.sql.model;


import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

public abstract class AbstractEntityAuditable<U,PK extends Serializable> implements Auditable<U,PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private U createdBy;
	private U lastModifiedBy;
	private DateTime creadtedDate;
	private DateTime lastModifiedDate;
	
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public U getCreatedBy() {
		// TODO Auto-generated method stub
		return this.createdBy;
	}

	@Override
	public DateTime getCreatedDate() {
		// TODO Auto-generated method stub
		return this.creadtedDate;
	}

	@Override
	public U getLastModifiedBy() {
		// TODO Auto-generated method stub
		return this.lastModifiedBy;
	}

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
	public void setCreatedDate(DateTime creadtedDate) {
		this.creadtedDate = creadtedDate;
		
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
