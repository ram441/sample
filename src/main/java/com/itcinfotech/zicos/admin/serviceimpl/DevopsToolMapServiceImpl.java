package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DevopsToolMapService;
import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.Tools;
import com.itcinfotech.zicos.sql.repository.DevopToolRepository;
import com.itcinfotech.zicos.sql.repository.DevopsToolRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.sql.repository.ToolsRepository;
import com.itcinfotech.zicos.utils.CICDUtils;

@Service
public class DevopsToolMapServiceImpl implements DevopsToolMapService {

	static final Logger log = LogManager.getLogger(DevopsToolMapServiceImpl.class.getName());

	@Autowired
	private CICDUtils cICDUtils;

	@Autowired
	private DevopsToolRepository devopsToolRepository;
	@Autowired
	private DevopToolRepository devopToolRepository;
	@Autowired
	private ProjectsRepository projectRepository;
	@Autowired
	private ToolsRepository toolsRepository;

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
		Tools tool = devopsTool.getTool();
		List<Tools> tools = new ArrayList<>();
		tools.add(tool);
		boolean status = createNewEnv(devopsTool.getProject().getProjectId(), tools);
		System.out.println(status + "==============================================");
		return null;
		// return devopsToolRepository.save(devopsTool);
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
	public DevopsTool saveDevopsToolDts(Long projectId, Long toolId, Long devopToolId) {

		return null;
	}

	public boolean createNewEnv(Long projectId, List<Tools> formTools) {
		boolean flag = false;
		int total = 0;
		if (formTools != null && formTools.size() > 0) {
			// Calling Shell script and reading the file
			if (cICDUtils.fetchToolConfigDtsByShellScriptCall(projectId, formTools)) {
				flag = true;
			} else {
				return flag;
			}
		} else {
			log.info("FormTools Data are empty...");
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean saveToolsConfigForNewEnv(List<DevopsTool> devopsTools) {
		if (devopsTools.isEmpty()) {
			return false;
		}
		List<Tools> toolsList = toolsRepository.findAll();
		Map<String, Tools> toolsMap = new HashMap<String, Tools>();
		if (toolsList != null && toolsList.size() > 0) {
			for (Tools tool : toolsList) {
				toolsMap.put(tool.getToolName().toLowerCase(), tool);
			}
		}
		for (DevopsTool devopsTool : devopsTools) {
			Tools tool = toolsMap.get(devopsTool.getTool().getToolName());
			devopsTool.setTool(tool);
		}
		devopsToolRepository.save(devopsTools);
		return true;
	}
}
