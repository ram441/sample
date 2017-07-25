package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DevopToolService;
import com.itcinfotech.zicos.sql.model.DevopTool;
import com.itcinfotech.zicos.sql.repository.DevopToolRepository;
import com.itcinfotech.zicos.utils.CICDUtils;
import com.itcinfotech.zicos.utils.Constants;

@Service
public class DevopToolServiceImpl implements DevopToolService{

	@Value("${proxy.ip_address}")
	public String proxyIpAddress;

	@Value("${proxy.port}")
	public Integer proxyPort;
	
	@Autowired
	private CICDUtils cICDUtils;
	
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
	public boolean validateConnectionConfig(DevopTool devopTool) {
		// TODO Auto-generated method stub
		return testExistingEnv(devopTool);
	}
	
	public boolean testExistingEnv(DevopTool formDevopToolPojo) {
		boolean flag=true;
		boolean status =false;
		DevopTool devopTool = new DevopTool();
		if(formDevopToolPojo!=null && formDevopToolPojo.getToolName()!=null && StringUtils.isNotEmpty(formDevopToolPojo.getToolName())){
			if(formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.JIRA_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.SONAR_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.BITBUCKET_TOOL)
					|| formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.JENKINS_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.CHEF_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.NEXUS_TOOL)){
				String urlPath="";
				if(formDevopToolPojo.getUrl()!= null && StringUtils.isNotEmpty(formDevopToolPojo.getUrl())){
					devopTool.setUrl(formDevopToolPojo.getUrl());
				}
				if(formDevopToolPojo.getUserName()!= null && StringUtils.isNotEmpty(formDevopToolPojo.getUserName())){
					devopTool.setUserName(formDevopToolPojo.getUserName());
				}
				if(formDevopToolPojo.getPassword()!= null && StringUtils.isNotEmpty(formDevopToolPojo.getPassword())){
					devopTool.setPassword(formDevopToolPojo.getPassword());
				}
				if(formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.JIRA_TOOL)){
					urlPath=Constants.JIRA_URLPATH;
				}
				if(formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.JIRA_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.BITBUCKET_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.CHEF_TOOL)){
					status = true;
				}
				if(formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.NEXUS_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.SONAR_TOOL) || formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.JENKINS_TOOL)){
					status = false;
				}
				
					return cICDUtils.getTestConnResponse(devopTool.getUrl()+urlPath, proxyIpAddress, proxyPort, devopTool.getUserName(), devopTool.getPassword(),status,formDevopToolPojo.getToolName());
				}else if(formDevopToolPojo.getToolName().equalsIgnoreCase(Constants.REDMINE_TOOL)){
					devopTool.setUrl(formDevopToolPojo.getUrl());
					flag=false;
			}else{
				flag=false;
			}
			
		}else{
			flag=false;
		}
			
		return flag;
 
	}
}
