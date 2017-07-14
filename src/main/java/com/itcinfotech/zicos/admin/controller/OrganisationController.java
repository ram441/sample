package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.OrganisationService;
import com.itcinfotech.zicos.sql.model.Organisation;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationService organisationService;
	
	@RequestMapping(value="/organisation/{id}", method =RequestMethod.GET)
	public  Organisation findOrganisationById(@PathVariable("id") Long id)  {
		return organisationService.findOrganisationById(id);
	}
	@RequestMapping(value="/organisation", method =RequestMethod.GET)
	public  List<Organisation> findAllOrganisations() {
		return organisationService.findAllOrganisations();
	}
/*	public  List<Organisation> findOrganisationByid(Long id) {
		
	}*/
	@RequestMapping(value="/organisation", method =RequestMethod.POST)
	public Organisation saveOrganisation(@RequestBody Organisation organisation) {
		return organisationService.saveOrganisation(organisation);
	}
	@RequestMapping(value="/organisation", method =RequestMethod.PUT)
	public Organisation updateOrganisation(@RequestBody Organisation organisation) {
		return organisationService.updateOrganisation(organisation);
	}
}
