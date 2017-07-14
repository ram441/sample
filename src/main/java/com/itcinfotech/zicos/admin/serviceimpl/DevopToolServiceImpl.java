package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DevopToolService;
import com.itcinfotech.zicos.sql.model.DevopTool;
import com.itcinfotech.zicos.sql.repository.DevopToolRepository;

@Service
public class DevopToolServiceImpl implements DevopToolService{

	@Autowired
	private DevopToolRepository devopToolRepository;
	@Override
	public DevopTool findDevopToolById(Long id) {
		// TODO Auto-generated method stub
		return devopToolRepository.findOne(id);
	}

	@Override
	public List<DevopTool> findAllDevopTools() {
		// TODO Auto-generated method stub
		return devopToolRepository.findAll();
	}

	@Override
	public List<DevopTool> findDevopToolByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DevopTool saveDevopTool(DevopTool devopTool) {
		// TODO Auto-generated method stub
		return devopToolRepository.save(devopTool);
	}

	@Override
	public Long saveDevopToolDts(DevopTool devopToolPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DevopTool updateDevopTool(DevopTool devopTool) {
		// TODO Auto-generated method stub
		return devopToolRepository.save(devopTool);
	}

	@Override
	public boolean testExistingEnv(DevopTool formDevopToolPojo,
			String proxyIpAddress, Integer proxyPort) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
