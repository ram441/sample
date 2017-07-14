package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DashboardComponentService;
import com.itcinfotech.zicos.sql.model.DashBoardComponents;
import com.itcinfotech.zicos.sql.repository.DashBoardComponentsRepository;

@Service
public class DashboardComponentServiceImpl implements DashboardComponentService {

	@Autowired
	private DashBoardComponentsRepository dashBoardComponentsRepository;
	@Override
	public DashBoardComponents findDashBoardComponentById(Long id) {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.findOne(id);
	}

	@Override
	public DashBoardComponents getDashBoardComponentByName(String componentName) {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.findByDashBoardComponentName(componentName);
	}

	@Override
	public List<DashBoardComponents> findAllDashBoardComponents() {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.findAll();
	}

	@Override
	public DashBoardComponents saveDashBoardComponent(
			DashBoardComponents boardComponent) {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.save(boardComponent);
	}

	@Override
	public DashBoardComponents updateDashBoardComponent(
			DashBoardComponents boardComponent) {
		// TODO Auto-generated method stub
		return dashBoardComponentsRepository.save(boardComponent);
	}
	
	
}
