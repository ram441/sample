package com.itcinfotech.zicos.nosql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.nosql.service.DashBoardComponentsService;
import com.itcinfotech.zicos.sql.model.DashBoardComponents;
import com.itcinfotech.zicos.sql.repository.DashBoardComponentsRepository;

@Service
public class DashBoardComponentsServiceImpl implements DashBoardComponentsService{

	@Autowired
	private DashBoardComponentsRepository dashBoardComponentsRepository;
	
	@Override
	public List<DashBoardComponents> loadDashBoardComponentsByRole(Long roleId) {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.findAll();
	}

}
