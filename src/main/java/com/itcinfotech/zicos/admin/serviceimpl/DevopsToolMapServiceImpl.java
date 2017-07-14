package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DevopsToolMapService;
import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.DevopsToolRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;

@Service
public class DevopsToolMapServiceImpl implements DevopsToolMapService{

	@Autowired
	private DevopsToolRepository devopsToolRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Override
	public DevopsTool findDevopsToolById(Long id) {
		// TODO Auto-generated method stub
		return devopsToolRepository.findOne(id);
	}

	@Override
	public List<DevopsTool> findAllDevopsTools() {
		// TODO Auto-generated method stub
		return devopsToolRepository.findAll();
	}

	@Override
	public List<DevopsTool> findDevopsToolByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DevopsTool saveDevopsTool(DevopsTool devopsTool) {
		// TODO Auto-generated method stub
		return devopsToolRepository.save(devopsTool);
	}

	@Override
	public DevopsTool updateDevopsTool(DevopsTool devopsTool) {
		// TODO Auto-generated method stub
		return devopsToolRepository.save(devopsTool);
	}

	@Override
	public List<DevopsTool> findDevopsToolByProjectId(Long id) {
		Projects project = projectRepository.findOne(id);
		return devopsToolRepository.findByProject(project);
	}

	@Override
	public DevopsTool saveDevopsToolDts(Long projectId, Long toolId,
			Long devopToolId) {
		
		return null;
	}
	
	
}
