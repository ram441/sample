package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "organisation_bu")
public class OrganisationBuConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long organisationBuConfigId;
	private Organisation organisation;
	private String buName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "bu_id", unique = true, nullable = false)
	public Long getOrganisationBuConfigId() {
		return organisationBuConfigId;
	}
	public void setOrganisationBuConfigId(Long organisationBuConfigId) {
		this.organisationBuConfigId = organisationBuConfigId;
	}
//	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "org_id",nullable=false)
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	@Column(name = "bu_name")
	public String getBuName() {
		return buName;
	}
	public void setBuName(String buName) {
		this.buName = buName;
	}

}
