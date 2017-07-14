package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.DashBoardComponents;


public interface DashboardComponentService {
	public  DashBoardComponents findDashBoardComponentById(Long id) ;
	public  DashBoardComponents getDashBoardComponentByName(String componentName);
	public  List<DashBoardComponents> findAllDashBoardComponents();	
	public DashBoardComponents saveDashBoardComponent(DashBoardComponents boardComponent);
	public DashBoardComponents updateDashBoardComponent(DashBoardComponents boardComponent);
}
