package com.itcinfotech.zicos.nosql.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.DashBoardComponents;

public interface DashBoardComponentsService {
public List<DashBoardComponents> loadDashBoardComponentsByRole(Long roleId);
}
