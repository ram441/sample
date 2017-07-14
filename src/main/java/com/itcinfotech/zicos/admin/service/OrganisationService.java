package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Organisation;

public interface OrganisationService {
	public  Organisation findOrganisationById(Long id) ;
	public  List<Organisation> findAllOrganisations();
	public  List<Organisation> findOrganisationByid(Long id);	
	public Organisation saveOrganisation(Organisation organisation);
	public Organisation updateOrganisation(Organisation organisation);
}
