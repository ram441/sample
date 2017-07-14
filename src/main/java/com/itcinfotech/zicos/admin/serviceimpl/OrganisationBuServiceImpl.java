package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.OrganisationBuService;
import com.itcinfotech.zicos.sql.model.OrganisationBuConfig;
import com.itcinfotech.zicos.sql.repository.OrganisationBuConfigRepository;

@Service
public class OrganisationBuServiceImpl implements OrganisationBuService {

	@Autowired
	private OrganisationBuConfigRepository organisationBuConfigRepository;

	@Override
	public OrganisationBuConfig findOrganisationBuById(Long id) {
		// TODO Auto-generated method stub
		return organisationBuConfigRepository.getOne(id);
	}

	@Override
	public List<OrganisationBuConfig> findAllOrganisationBus() {
		// TODO Auto-generated method stub
		return organisationBuConfigRepository.findAll();
	}

	@Override
	public List<OrganisationBuConfig> findOrganisationBusByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganisationBuConfig saveOrganisationBu(
			OrganisationBuConfig organisationBu) {
		// TODO Auto-generated method stub
		return organisationBuConfigRepository.save(organisationBu);
	}

	@Override
	public OrganisationBuConfig updateOrganisationBu(
			OrganisationBuConfig organisationBu) {
		// TODO Auto-generated method stub
		return organisationBuConfigRepository.save(organisationBu);
	}

	@Override
	public OrganisationBuConfig findOrganisationBuByOrgIdBuName(Long orgId,
			String buName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrganisationBuConfig findOrganisationBuByBuid(Long orgBuId) {
		// TODO Auto-generated method stub
		return null;
	}

}
