package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.DevopTool;

public interface DevopToolService {
	public  DevopTool findDevopToolById(Long id) ;
	public  List<DevopTool> findAllDevopTools();
	public  List<DevopTool> findDevopToolByid(Long id);	
	public DevopTool saveDevopTool(DevopTool devopTool);
	public Long saveDevopToolDts(DevopTool devopToolPojo);
	public DevopTool updateDevopTool(DevopTool devopTool);
	public boolean validateConnectionConfig(DevopTool devopTool);
}
