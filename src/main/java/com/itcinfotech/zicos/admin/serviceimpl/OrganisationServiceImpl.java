package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.OrganisationService;
import com.itcinfotech.zicos.sql.model.Organisation;
import com.itcinfotech.zicos.sql.repository.OrganisationRepository;

@Service
public class OrganisationServiceImpl implements OrganisationService{

	@Autowired
	private OrganisationRepository organizationRepository;
	@Override
	public Organisation findOrganisationById(Long id) {
		// TODO Auto-generated method stub
		return organizationRepository.findOne(id);
	}

	@Override
	public List<Organisation> findAllOrganisations() {
		// TODO Auto-generated method stub
		return organizationRepository.findAll();
	}

	@Override
	public List<Organisation> findOrganisationByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Organisation saveOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return organizationRepository.save(organisation);
	}

	@Override
	public Organisation updateOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		return organizationRepository.save(organisation);
	}
	
	
}
