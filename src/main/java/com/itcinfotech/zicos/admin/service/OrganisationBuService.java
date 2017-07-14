package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;

public interface OrganisationBuService {
	public  OrganisationBuConfig findOrganisationBuById(Long id) ;
	public  List<OrganisationBuConfig> findAllOrganisationBus();
	public  List<OrganisationBuConfig> findOrganisationBusByid(Long id);	
	public OrganisationBuConfig saveOrganisationBu(OrganisationBuConfig organisationBu);
	public OrganisationBuConfig updateOrganisationBu(OrganisationBuConfig organisationBu);
	
	public  OrganisationBuConfig findOrganisationBuByOrgIdBuName(Long orgId, String buName);
	public  OrganisationBuConfig findOrganisationBuByBuid(Long orgBuId);
}
