package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.DevopsToolMapService;
import com.itcinfotech.zicos.sql.model.DevopTool;
import com.itcinfotech.zicos.sql.model.DevopsTool;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.Tools;
import com.itcinfotech.zicos.sql.repository.DevopToolRepository;
import com.itcinfotech.zicos.sql.repository.DevopsToolRepository;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.sql.repository.ToolsRepository;
import com.itcinfotech.zicos.utils.CICDUtils;

@Service
public class DevopsToolMapServiceImpl implements DevopsToolMapService{

	static final Logger log = LogManager.getLogger(DevopsToolMapServiceImpl.class.getName());
	
	@Value("${linux.hostname}")
	public String linuxHostName;
	
	@Value("${linux.username}")
	public String linuxUserName;
	
	@Value("${linux.password}")
	public String linuxPassword;
	
	@Value("${linux.port}")
	public Integer linuxPort; 
	
	
	@Value("${linux.shellScriptCmd}")
	public String shellScriptCmd; 
	
	@Value("${linux.fetchTextDtlsCmd}")
	public String fetchTextDtlsCmd; 
	

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
		boolean status = createNewEnv(devopsTool.getProject().getProjectId(),tools);
		System.out.println(status+"==============================================");
		return null;
		//return devopsToolRepository.save(devopsTool);
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
	
	public boolean createNewEnv(Long projectId, List<Tools> formTools) {
		boolean flag= false;
		int total=0;
		System.out.println(linuxHostName+"-linuxUserName-"+linuxUserName+" -linuxPassword- "+linuxPassword+"-linuxPort--"+linuxPort+"-shellScriptCmd-"+shellScriptCmd+"-fetchTextDtlsCmd-"+fetchTextDtlsCmd);
		if(formTools!= null && formTools.size()>0){
				//Calling Shell script and reading the file 
					List<DevopTool> lstDts=CICDUtils.fetchToolConfigDtsByShellScriptCall(formTools,linuxHostName,linuxUserName,linuxPassword,linuxPort,shellScriptCmd,fetchTextDtlsCmd);
					//Fetching all the tools and keep in Map<ToolName,toolId> 
					List<Tools> toolsList = toolsRepository.findAll();
					Map<String,Long> toolsDts =  new HashMap<String,Long>();
					if(toolsList!=null && toolsList.size()>0){
						for(Tools t:toolsList){
							toolsDts.put(t.getToolName(),t.getToolId());
						}
					}
						if(lstDts!= null && lstDts.size()>0 && formTools!= null && formTools.size()>0 && lstDts.size()==formTools.size()){
								for(DevopTool dtPojo:lstDts){
									if(dtPojo!=null && dtPojo.getToolName()!=null && StringUtils.isNotEmpty(dtPojo.getToolName())){
										DevopTool devTool = devopToolRepository.save(dtPojo);	
										Long devopToolId = devTool.getDevopToolId();
												if(devopToolId!= null && devopToolId!=0){
													if(toolsDts!=null && toolsDts.size()>0 && toolsDts.containsKey(dtPojo.getToolName())){
														Long toolId = toolsDts.get(dtPojo.getToolName());
														DevopTool devoTools =  new DevopTool();
														devoTools.setDevopToolId(devopToolId);
														Projects project = new Projects();
														project.setProjectId(projectId);
														DevopsTool devosTools = new DevopsTool();
														
														Tools tools = new Tools();
														tools.setToolId(toolId);
														devosTools.setProject(project);
														devosTools.setTool(tools);
														devosTools.setDevopTool(devoTools);
														DevopsTool savedDeveopsTool = devopsToolRepository.save(devosTools);
															if(savedDeveopsTool!=null){
																total++;
																log.info("Devops Tools Succesfully..."+devopToolId);
															}else{
																	total--;
																	log.info("Devops Tools Save Failed..."+devopToolId);
																	return flag=false;
															}
														}else{
															log.info("Failed Map dts toolId null ...");
															return flag=false;
														}
													}else{
														log.info("No Tool Exists in the DB...");
														return flag=false;
													}
										}else{
											log.info("Reading Txt Dts Tool Name cannot be Empty.");
											return flag=false;
										}
									}
									if(total==lstDts.size() && formTools.size()==lstDts.size()){
										log.info(" Success Saving Tools, Devop Tool,Devops Tool ...");
										return flag=true;
									}else{
										log.info(total+":total.. Failed for Saving Tools, Devop Tool,Devops Tool .. lstDts.size():"+lstDts.size());
										return flag=true;
									}
							}else{
								log.info(formTools.size()+":formTools.size()..Failed while Calling Shell script and reading the file ...");
								return flag=false;
							}
			}else{
				log.info("FormTools Data are empty...");
				return flag=false;
			}
	}
}
