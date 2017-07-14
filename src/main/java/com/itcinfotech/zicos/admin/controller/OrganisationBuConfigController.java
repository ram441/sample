package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.OrganisationBuService;
import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;
@RestController
public class OrganisationBuConfigController {

	@Autowired
	private OrganisationBuService organisationBuService;
	
	@RequestMapping(value="/organisationbuserive/{id}", method =RequestMethod.GET)
	public  OrganisationBuConfig findOrganisationBuById(@PathVariable("id") Long id) {
		return organisationBuService.findOrganisationBuById(id);
	}
	@RequestMapping(value="/organisationbuserive", method =RequestMethod.GET)
	public  List<OrganisationBuConfig> findAllOrganisationBus() {
		return organisationBuService.findAllOrganisationBus();
	}
	/*public  List<OrganisationBuConfig> findOrganisationBusByid(Long id) {
		return organisationBuService.findOrganisationBusByid(id);
	}*/
	@RequestMapping(value="/organisationbuserive", method =RequestMethod.POST)
	public OrganisationBuConfig saveOrganisationBu(@RequestBody OrganisationBuConfig organisationBu) {
		return organisationBuService.saveOrganisationBu(organisationBu);
	}
	@RequestMapping(value="/organisationbuserive", method =RequestMethod.PUT)
	public OrganisationBuConfig updateOrganisationBu(@RequestBody OrganisationBuConfig organisationBu) {
		return organisationBuService.updateOrganisationBu(organisationBu);
	}
	@RequestMapping(value="/organisationbuserive",params={"orgId","buName"}, method =RequestMethod.GET)
	public  OrganisationBuConfig findOrganisationBuByOrgIdBuName(@RequestParam("orgId") Long orgId,@RequestParam("buName") String buName) {
		return organisationBuService.findOrganisationBuByOrgIdBuName(orgId, buName);
	}
	@RequestMapping(value="/organisationbuserive",params={"orgBuId",}, method =RequestMethod.GET)
	public  OrganisationBuConfig findOrganisationBuByBuid(@RequestParam("orgBuId") Long orgBuId) {
		return organisationBuService.findOrganisationBuByBuid(orgBuId);
	}
}
