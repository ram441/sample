package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.DevopsTool;

public interface DevopsToolMapService {
	public  DevopsTool findDevopsToolById(Long id) ;
	public  List<DevopsTool> findAllDevopsTools();
	public  List<DevopsTool> findDevopsToolByid(Long id);	
	public DevopsTool saveDevopsTool(DevopsTool devopsTool);
	public DevopsTool updateDevopsTool(DevopsTool devopsTool);
	public List<DevopsTool> findDevopsToolByProjectId(Long id);
	public DevopsTool saveDevopsToolDts(Long projectId, Long toolId,
			Long devopToolId);
	public boolean saveToolsConfigForNewEnv(List<DevopsTool> devopsTools);
}
