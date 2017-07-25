package com.itcinfotech.zicos.pipeline.serviceimpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.itcinfotech.zicos.admin.service.ProjectService;
import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.repository.JobsPipelineRepository;
import com.itcinfotech.zicos.pipeline.repository.PipeLineTxnRepository;
import com.itcinfotech.zicos.pipeline.repository.PipelineDefRepository;
import com.itcinfotech.zicos.pipeline.service.PipeLineService;
import com.itcinfotech.zicos.pipeline.service.PipeLineTxnService;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.utils.ConstantPropertiesUtils;
import com.itcinfotech.zicos.utils.JenkinUtils;
import com.itcinfotech.zicos.utils.PipeStateUpdater;

@Service
public class PipeLineServiceImpl implements PipeLineService {

	static final Logger logger = LogManager.getLogger(PipeLineServiceImpl.class.getName());
	@Autowired
	private JobsPipelineRepository jobsPipelineRepository;
	@Autowired
	private PipeLineTxnRepository pipeLineTxnRepository;
	@Autowired
	private PipelineDefRepository pipelineDefRepository;
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private PipeLineTxnService pipeLineTxnService;
	@Autowired
	private PipelineDefService pipelineDefService;
	@Autowired
	private PipeStateUpdater pipeStateUpdater;
	@Autowired
	private ProjectsRepository projectsRepository;
	
	@Autowired
	JenkinUtils jenkinUtils;
	@Autowired
	private ConstantPropertiesUtils constantPropertiesUtils;
	@Override
	public List<JobsPipeline> loadAllPipelines() {
		// TODO Auto-generated method stub
		return jobsPipelineRepository.findAll();
	}

	@Override
	public JobsPipeline findPipeLineByProjectId(Long projectId) {

		Projects viewProject = new Projects();
		viewProject.setProjectId(projectId);
		return jobsPipelineRepository.findByProject(viewProject).get(0);
	}

	@Override
	public JobsPipeline savePipeLine(JobsPipeline pipeline) {
		// TODO Auto-generated method stub
		return jobsPipelineRepository.save(pipeline);
	}

	@Override
	public JobsPipeline updatePipeLine(JobsPipeline pipeline) {
		return jobsPipelineRepository.save(pipeline);
	}

	@Override
	public List<JobsPipeline> getAllJobsPipeLineByProjectIdAndpieDefId(Long projectId, Long pipeDefId, Long envId) {
		Projects project = new Projects();
		project.setProjectId(projectId);
		PipelineDef pipeLineDef = new PipelineDef();
		pipeLineDef.setPipelineDefId(pipeDefId);
		Environment environment = new Environment();
		environment.setEnvId(envId);
		return jobsPipelineRepository.findByProjectAndPipelineDefAndEnv(project, pipeLineDef,envId);
	}

	@Override
	public List<PipeLineTxn> getPipelineTransactionHistory(Long pipeDefId) {
		PipelineDef pipeLineDef = new PipelineDef();
		pipeLineDef.setPipelineDefId(pipeDefId);
		return pipeLineTxnRepository.findByPipelineDef(pipeLineDef);
	}

	@Override
	public PipelineDef savePipeLineDef(PipelineDef pipelineDef) {
		// TODO Auto-generated method stub
		return pipelineDefRepository.save(pipelineDef);
	}

	@Override
	public boolean isPipeRunning(Long pipeDefId) {
		PipelineDef pipeLineDef = pipelineDefRepository.getOne(pipeDefId);
		if(pipeLineDef.getCurrentStatus().equals("Running")){
			return true;
		}else if(pipeLineDef.getCurrentStatus().equals("NA") || pipeLineDef.getCurrentStatus().equals("Completed")){
			return false;
		}
		return false;
	}

	public void runPipeLine(Long projectId,Long pipelineDefId,  Long envId) {
		

		
		if(jenkinUtils.isPipeRunning(pipelineDefId)){
			return;
		}
				
		Projects project = projectService.findProjectById(projectId);
		if(project == null){
			logger.info("No Project is exited with given Id");
			return;
		}
		//Fetch the assigned jobs to the pipeline 
		List<JobsPipeline> jobsPipelines = getAllJobsPipeLineByProjectIdAndpieDefId(projectId, pipelineDefId,envId );
		logger.info("List of assigned jobs to the pipeline view : " + jobsPipelines.size());
		if(jobsPipelines == null || jobsPipelines.size() == 0){
			logger.info("No Jobs assigned / No Pipe Line view created to this project");
			return;
		}
			//Step 2) Save all the jobs into pipeline_transaction table, which are assigned to currently selected pipe line
			PipeLineTxn pipeLineTransaction = null;
			JobsPipeline jobsPipeline = null;
			PipelineDef pipelineDef = new PipelineDef();
			List<PipeLineTxn> pipelineTnxList = new ArrayList<>();
			for(JobsPipeline existedJobPipeline : jobsPipelines){
				pipeLineTransaction = new PipeLineTxn();
				
				//Setting the selected to pipe line transaction
				jobsPipeline = new JobsPipeline();
				jobsPipeline.setJobId(existedJobPipeline.getJobId());
				
				Long pipeDefExecId = existedJobPipeline.getPipelineDef().getPipeExecId();
				Long pipeUpdatedExecId = pipeDefExecId+1;
				
				pipeLineTransaction.setIsAssigned("NE");
				pipeLineTransaction.setPipeExecId(pipeUpdatedExecId);
				pipeLineTransaction.setJobsPipeline(jobsPipeline);
//				pipeLineTransaction.setPipeExecId(pipeExecId);
				pipelineTnxList.add(pipeLineTransaction);
			}
			
			pipelineDef.setPipelineDefId(jobsPipelines.get(0).getPipelineDef().getPipelineDefId());
			pipelineDef.setPipeExecId(jobsPipelines.get(0).getPipelineDef().getPipeExecId());
			pipelineDef.setCurrentStatus("Running");
			for(PipeLineTxn pipeTxn:pipelineTnxList ) {
				pipeTxn.setPipelineDef(pipelineDef);
			}
			//updating and inserting pipeline defination
			PipelineDef updatedPipelineDef = pipelineDefService.updatePipelineDef(pipelineDef);
			
			List<PipeLineTxn> savedPipeLineTxn = pipeLineTxnService.savePipeLineTransactins(pipelineTnxList);
			
			Map<String, Map<String, Map<String,String>>> initialStateMap = jenkinUtils.getPipeStatusDtls(constantPropertiesUtils.jenkinUrl,constantPropertiesUtils.proxyIpAddress,constantPropertiesUtils.proxyPort,project.getProjectName());
			
			//Executing pipe thruough first job
			try {
				runJenkinsPipe();
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			pipeStateUpdater.monitorPipe(initialStateMap,project.getProjectName(),jobsPipeline,pipelineDef);
		   return;
	
		
	}
	
	public void runJenkinsPipe() throws ParseException, IOException{
		try {
			
			JobsPipeline jobsPipeLine = fetchByisFirstJob(true);
			String firstJobToBeRun= jobsPipeLine.getJobName();
			logger.info("firstJobToBeRun : "+firstJobToBeRun);
			boolean jenkins = jenkinUtils.runJenkinsJob(constantPropertiesUtils.jenkinHost, constantPropertiesUtils.jenkinPort,constantPropertiesUtils.proxyIpAddress,constantPropertiesUtils.proxyPort,constantPropertiesUtils.proxyScheme,constantPropertiesUtils.targetScheme,firstJobToBeRun);
		} catch (ParserConfigurationException | SAXException e) {
			logger.error("ParserConfigurationException | SAXException --- > "+e.getMessage());
			e.printStackTrace();
		}
	}

	public JobsPipeline fetchByisFirstJob(boolean b) {
		return jobsPipelineRepository.findByIsFirstJob(true);
	}

	@Override
	public List<JobsPipeline> findJobsByProjId(Long projectId) {
		// TODO Auto-generated method stub
		Projects projects = projectsRepository.findByProjectId(projectId);
		return jobsPipelineRepository.findByProject(projects);
	}	
}
