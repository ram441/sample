package com.itcinfotech.zicos.pipeline.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.itcinfotech.zicos.admin.service.EnvironmentService;
import com.itcinfotech.zicos.admin.service.JobsService;
import com.itcinfotech.zicos.admin.service.ProjectService;
import com.itcinfotech.zicos.nosql.model.Jenkin;
import com.itcinfotech.zicos.nosql.service.JenkinsService;
import com.itcinfotech.zicos.pipeline.model.Environment;
import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.service.PipeLineService;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;
import com.itcinfotech.zicos.sql.model.Build;
import com.itcinfotech.zicos.sql.model.Jobs;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.utils.ConstantPropertiesUtils;
import com.itcinfotech.zicos.utils.JenkinUtils;
import com.itcinfotech.zicos.utils.PipeStateUpdater;

@RestController
public class JenkinJsonController {

	static final Logger logger = LogManager.getLogger(JenkinJsonController.class.getName());

	@Autowired
	ConstantPropertiesUtils databasePropertyUtils;
	@Autowired
	private JenkinsService jenkinsService;
	@Autowired
	private EnvironmentService envService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	PipelineDefService pipelineDefService;
	@Autowired
	private JobsService jobsService;

	@Autowired
	private PipeLineService jobsPipelineService;
	
	@Autowired
	private JenkinUtils jenkinsUtilService;

	@RequestMapping(value = "/jenkinsjsondata", method = RequestMethod.GET)
	public void saveJenkinData(){
		try {
			List<Jenkin> jenkinApiData= null;
			//getting jenkins data from jenkins server
			jenkinApiData = jenkinsUtilService.createJenkinsJsonData(databasePropertyUtils.jenkinUrl,jenkinsService, databasePropertyUtils.proxyIpAddress,databasePropertyUtils.proxyPort);
			for(Jenkin list : jenkinApiData){
				logger.info("JENKINS API LIST: "+list.toString());
			}

			if(jenkinApiData != null){
				logger.info(" jenkin Size : "+jenkinApiData.size());
				for(Jenkin jenkins: jenkinApiData){
					logger.info(jenkins.getBuildId());
					logger.info(jenkins.getLastSuccessBuildDuration());
					logger.info(jenkins.getJob());
					//getting jenkin collection data from mongodb
					Jenkin jenkinDbData = jenkinsService.getJenkinData(jenkins.getBuildId(),jenkins.getJob());
					
					if(jenkinDbData !=null){
						if(jenkins.getBuildName() !=null){
							logger.info("API build name : "+jenkins.getBuildName());
							jenkinDbData.setBuildName(jenkins.getBuildName());
							logger.info("DB build name : "+jenkinDbData.getBuildName());
						}
						if(jenkins.getJob() !=null)
							jenkinDbData.setJob(jenkins.getJob());
						if(jenkins.getBuildId()!=null){
							logger.info("API build Id : "+jenkins.getBuildName());
							jenkinDbData.setBuildId(jenkins.getBuildId());
							logger.info("DB build Id : "+jenkinDbData.getBuildId());
						}
						/*if(jenkins.getTimestamp() !=null)
						dbJenkinData.setTimestamp(jenkins.getTimestamp());*/
						if(jenkins.getBuildNumber() !=null)
							jenkinDbData.setBuildNumber(jenkins.getBuildNumber());
						if(jenkins.getBuildDuration() !=null)
							jenkinDbData.setBuildDuration(jenkins.getBuildDuration());
						
						if(jenkins.getTimestamp() !=null)
							jenkinDbData.setTimestamp(jenkins.getTimestamp());
						
						if(jenkins.getResult() !=null)
							jenkinDbData.setResult(jenkins.getResult());
						if(jenkins.getLastBuildDuration() !=null)
							jenkinDbData.setLastBuildDuration(jenkins.getLastBuildDuration());
						if(jenkins.getLastSuccessBuildDuration() !=null)
							jenkinDbData.setLastSuccessBuildDuration(jenkins.getLastSuccessBuildDuration());
						if(jenkins.getLastUnSuccessBuildDuration() !=null)
							jenkinDbData.setLastUnSuccessBuildDuration(jenkins.getLastUnSuccessBuildDuration());
						logger.info("ready to update jenkin last successful duration : "+jenkinDbData.getBuildName()+" and "+jenkinDbData.buildId);
						jenkinsService.update(jenkinDbData);
					}else{

						logger.info("ready to save jenkin object : "+jenkins.getJob()+", last success build duration : "+jenkins.getBuildId());
						jenkinsService.saveJenkinsData(jenkins);
					}
				}
			}
		}catch (Exception e) {
			logger.error("Exception @@ : "+e.getMessage());
			e.printStackTrace();
		}
		
	}


	@RequestMapping(value = "/jenkinsdbcollectiondata", method = RequestMethod.GET)
	public  ResponseEntity<List<Jenkin>> getJenkinAllCollectionData() throws ParseException{
		logger.info("::::::::: Fetch all jenkins database collection data ::::::::: ");
		List<Jenkin> dataList = jenkinsService.getJenkinAllCollectionData();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Jenkin>>(dataList,headers,HttpStatus.OK);
	}

	
	/**
	 * this function for get job names from mongodb
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/fetchjobnames", method = RequestMethod.GET)
	public  List<Jenkin> getJobNames() throws ParseException{
		logger.info("::::::::: Fetch all distinct job names from Mongo jenkin collection ::::::::: ");
		Jenkin jenkinForm = null;
		List<Jenkin> jobNames = jenkinsService.getJobNames();
		saveJobNameinSql(jobNames);
		return jobNames;
		
	}

	@RequestMapping(value = "/getAllViewProjectNames" , method = RequestMethod.GET)
	public @ResponseBody List<String> getAllViewNames(){
				List<String> viewProjNames = jenkinsUtilService.getAllViewNames(databasePropertyUtils.jenkinUrl,jenkinsService, databasePropertyUtils.proxyIpAddress,databasePropertyUtils.proxyPort);
				logger.info("viewProjNames : "+viewProjNames);
		return viewProjNames;
	}
	
	/**
	 * @param jobNames
	 * job names fetching from mongo db and saving into sql jobs table.
	 * 
	 */
	public void saveJobNameinSql(List<Jenkin> jobNames){
		Jobs job = null;
		Projects project = null;
		if(jobNames != null){
			logger.info(" list of job names Size : "+jobNames.size());
			for(Jenkin jobs: jobNames){
				try{
					job = new Jobs();
					project = new Projects();
					project.setProjectId(1L);
					job.setJobName(jobs.getJob());
					job.setProjectId(project);
					jobsService.saveJobs(job);
				}catch(Exception e){
					logger.error("Exception while saving jenkin jobs in sqldb : "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * jobs associated with project are coming from jenkins as a json response
	 * @param projectName
	 * @return
	 */
	public Set<String> getJobsByViewProject(String projectName){
		Set<String> jobsAssociatedProject = jenkinsUtilService.extractJobsFromViewProject(databasePropertyUtils.jenkinUrl,jenkinsService, databasePropertyUtils.proxyIpAddress,databasePropertyUtils.proxyPort,projectName);
		logger.info("jobsAssociatedProject : "+jobsAssociatedProject);
		return jobsAssociatedProject;
	}
	
//	@RequestMapping(value = "/identifyFirstJobForPipeline" , method = RequestMethod.GET)
	public String identifyFirstJobForPipeline(String jobName){
				String firstJob = jenkinsUtilService.identifyFirstJobForPipeline(databasePropertyUtils.jenkinUrl, jenkinsService, databasePropertyUtils.proxyIpAddress, databasePropertyUtils.proxyPort,jobName);
				logger.info("firstJob from jenkins server upstream jobs are zero : "+firstJob);
		return firstJob;
	}
	
	/**
	 * job names saving into mysql jobs_pipeline table.
	 * @param jobNames
	 */
	public void saveViewJobsByProject(Set<String> jobsAssociatedWithProj,Long projectId,Long envId){
		JobsPipeline job = null;
		Projects project = null;
		Environment environment = null;
		if(jobsAssociatedWithProj != null){
			for(String jobs: jobsAssociatedWithProj){
					job = new JobsPipeline();
					project = new Projects();
					project.setProjectId(projectId);
					job.setProject(project);
					environment = new Environment();
					environment.setEnvId(envId);
					job.setEnv(environment);
					job.setJobName(jobs);
					job.setFirstJob(false);
					if(identifyFirstJobForPipeline(jobs) != null){
						job.setFirstJob(true);
					}
					jobsPipelineService.savePipeLine(job);
			}
		}
	}
	
	/**
	 * @return
	 * @throws ParseException
	 * fetching lastBuild status(LastBuildDuration,LastSuccessBuildDuration,LastUnSuccessBuildDuration) duration from Mongo.
	 * used for DashBoard UI List of Jobs Table
	 * 
	 */
	@RequestMapping(value = "/fetchLastBuildStatus", method = RequestMethod.GET)
	public  List<Jenkin> getLastBuildStatus() throws ParseException{
		Jenkin jenkinForm = null;
		List<Jenkin> jenkinForms = new ArrayList<Jenkin>();
		//all job names coming from mongodb
		List<Jenkin> jobNames = jenkinsService.getJobNames();
		for(Jenkin jenkin : jobNames){
			jenkinForm = new Jenkin();
			String jobName = jenkin.getJob();
			jenkinForm.setJob(jobName);
			List<Jenkin> lastBuildstatus= jenkinsService.getLastBuildStatus(jobName);
			for(Jenkin lastBuildDetails: lastBuildstatus){
				jenkinForm.setLastBuildDuration(lastBuildDetails.getLastBuildDuration());
				jenkinForm.setLastSuccessBuildDuration(lastBuildDetails.getLastSuccessBuildDuration());
				jenkinForm.setLastUnSuccessBuildDuration(lastBuildDetails.getLastUnSuccessBuildDuration());
			}
			jenkinForms.add(jenkinForm);
		}
		return jenkinForms;
	}

	@RequestMapping(value = "/fetchindividualjobbuilddetails/{jobName}", method = RequestMethod.GET)
	public List<Jenkin> getIndividualJobBuildDetails(@PathVariable("jobName") String jobName) throws ParseException{
		Jenkin jenkinForm = null;
		List<Jenkin> jenkinForms = new ArrayList<Jenkin>();
		List<Jenkin> individualBuildstatus=	jenkinsService.getIndividualBuildDetails(jobName);
		for(Jenkin individualBuildDetails: individualBuildstatus){
			jenkinForm = new Jenkin();
			jenkinForm.setJob(jobName);
			jenkinForm.setBuildId(individualBuildDetails.getBuildId());
			jenkinForm.setBuildNumber(individualBuildDetails.getBuildNumber());
			jenkinForm.setDuration(individualBuildDetails.getBuildDuration());
			jenkinForm.setTimestamp(individualBuildDetails.getTimestamp());
			jenkinForms.add(jenkinForm);
		}
		return jenkinForms;
	}

	/*get all job details associated with project by passing project Id*/
	@RequestMapping(value = "/fetchjoblistforpipeline/{projectId}/{envId}", method = RequestMethod.GET)
	public  List<JobsPipeline> fetchJobLIstForPipeline(@PathVariable("projectId") Long projectId,@PathVariable("envId") Long envId) throws ParseException{
		Set<String> jobNames = null;
		List<JobsPipeline> jobsPipelineLst = null;
		
		jobsPipelineLst = jobsPipelineService.findJobsByProjId(projectId);
		
		if(jobsPipelineLst != null && jobsPipelineLst.size() > 0){
				return jobsPipelineLst;
		}
		//jobNames are coming from jenkins server direct call
		Projects viewProjNames = projectService.findProjectById(projectId);
		jobNames = getJobsByViewProject(viewProjNames.getProjectName());
		logger.info("set of jobNames "+jobNames+ "job names size : "+jobNames.size());
		saveViewJobsByProject(jobNames,viewProjNames.getProjectId(),envId);
		jobsPipelineLst = jobsPipelineService.findJobsByProjId(projectId);
		if(jobsPipelineLst == null){
			logger.info("no jobs found");
			return null;
		}
		return jobsPipelineLst;
	}
	/**
	 * it will fetch all jobs associated with project
	 * @param projectId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/fetchindividualjobstatus/{projectId}", method = RequestMethod.GET)
	public  List<Jenkin> getIndividualJobStatus(@PathVariable("projectId") Long projectId) throws ParseException{
		Jenkin jenkinForm = null;
		Build buildDetails = null;
		List<Jenkin> jenkinForms = new ArrayList<Jenkin>();
		List<JobsPipeline> jobNames = jobsPipelineService.findJobsByProjId(projectId);
		Projects viewProjNames = projectService.findProjectById(projectId);
		logger.info("View Project names : "+viewProjNames);
		for(JobsPipeline jobName : jobNames) {
			jenkinForm = new Jenkin();
			jenkinForm.setProjectName(viewProjNames.getProjectName());
			jenkinForm.setJob(jobName.getJobName());
			List<Build> buildList = new ArrayList<Build>();
			List<Jenkin> individualBuildstatus=	jenkinsService.getIndividualBuildDetails(jobName.getJobName());
			logger.info("size of builds "+individualBuildstatus.size() + "for Job "+jobName);
			for(Jenkin individualBuildDetails: individualBuildstatus){
				buildDetails = new Build();
				buildDetails.setJobName(jobName.getJobName());
				buildDetails.setBuildId(individualBuildDetails.getBuildId());
				buildDetails.setBuildNumber(individualBuildDetails.getBuildNumber());
				buildDetails.setDuration(jenkinsUtilService.getDurationBreakdown(Long.valueOf(individualBuildDetails.getBuildDuration())));
				if(individualBuildDetails.getTimestamp() != null){
				BigDecimal timestmp = new BigDecimal(individualBuildDetails.getTimestamp());
				buildDetails.setTimestamp(jenkinsUtilService.milliToDate(timestmp.longValue()));
				}	
				buildList.add(buildDetails);
			}
			jenkinForm.setBuildDetails(buildList);
			Integer totalBuildCount = jenkinsService.getTotalBuildCount(jobName.getJobName());
			Integer successBuildCount = jenkinsService.getSuccessBuildCount(jobName.getJobName());
			Integer failedBuildCount = jenkinsService.getFailedBuildCount(jobName.getJobName());
			Integer abortedBuildCount = jenkinsService.getAbortedBuildCount(jobName.getJobName());
			List<Jenkin> lastBuildstatus= jenkinsService.getLastBuildStatus(jobName.getJobName());
			for(Jenkin lastBuildDetails: lastBuildstatus){
				jenkinForm.setLastBuildDuration(jenkinsUtilService.getDurationBreakdown(Long.valueOf(lastBuildDetails.getLastBuildDuration())));
				//jenkinForm.setLastBuildDuration(jenkinsUtilService.getDurationBreakdown(Long.valueOf(lastBuildDetails.getLastBuildDuration())));
				jenkinForm.setLastSuccessBuildDuration(jenkinsUtilService.getDurationBreakdown(Long.valueOf(lastBuildDetails.getLastSuccessBuildDuration())));
				jenkinForm.setLastUnSuccessBuildDuration(jenkinsUtilService.getDurationBreakdown(Long.valueOf(lastBuildDetails.getLastUnSuccessBuildDuration())));
			}
			
			double failureRate = jenkinsUtilService.getFailureRate(failedBuildCount,totalBuildCount);
			jenkinForm.setTotalBuildCount(totalBuildCount);
			jenkinForm.setSuccessBuildCount(successBuildCount);
			jenkinForm.setFailedBuildCount(failedBuildCount);
			jenkinForm.setAbortedBuildCount(abortedBuildCount);
			jenkinForm.setFailureRate(failureRate);
			jenkinForms.add(jenkinForm);
		}
			return jenkinForms;
}

	/**
	 * @return
	 * @throws ParseException
	 * for dashboard line chart 
	 * fetching all jobs names and build count(total, success, failed, aborted) from Mongo
	 */
	@RequestMapping(value = "/fetchbuildcount", method = RequestMethod.GET)
	public List<Jenkin> getBuildNames() throws ParseException{
		Jenkin jenkinForm = null;
		List<Jenkin> jenkinForms = new ArrayList<Jenkin>();
		List<Jenkin> jobNames = jenkinsService.getJobNames();
		for(Jenkin jenkin : jobNames){
			jenkinForm = new Jenkin();
			String jobName = jenkin.getJob();
			jenkinForm.setJob(jobName);
			int totalBuildCount = jenkinsService.getTotalBuildCount(jobName);
			int successBuildCount = jenkinsService.getSuccessBuildCount(jobName);
			int failedBuildCount = jenkinsService.getFailedBuildCount(jobName);
			int abortedBuildCount = jenkinsService.getAbortedBuildCount(jobName);
			Jenkin jenkinDuration = jenkinsService.getJenkinByBuildName(jobName);


			jenkinForm.setDuration(jenkinDuration.getBuildDuration());
			logger.info(" TotalBuildCount :"+totalBuildCount+" SuccessBuildCount :"+successBuildCount+" FailedBuildCount :"+failedBuildCount+" AbortedBuildCount :"+abortedBuildCount);
			double failureRate = jenkinsUtilService.getFailureRate(failedBuildCount,totalBuildCount);
			jenkinForm.setTotalBuildCount(totalBuildCount);
			jenkinForm.setSuccessBuildCount(successBuildCount);
			jenkinForm.setFailedBuildCount(failedBuildCount);
			jenkinForm.setAbortedBuildCount(abortedBuildCount);
			jenkinForm.setFailureRate(failureRate);
			jenkinForms.add(jenkinForm);
		}
		return jenkinForms;
	}

	/**
	 * @return
	 * total build names and count 
	 * success build names and count
	 * failed build names and count
	 * aborted build names and count
	 * 
	 * this function is used to progress bar in dashboard UI
	 */
	@RequestMapping(value = "/jobnamesandbuildcount", method = RequestMethod.GET)
	public  Jenkin getTotalBuildCountAndNames() {/*
		Jenkin jenkinForm = new Jenkin();

		//total build names and count list
		List<Jenkin> totalBuildNamesList =jenkinsService.getTotalBuildNamesAndCount();
		LinkedHashMap<String, Integer> totalBuildNamesAndCount = new LinkedHashMap<String, Integer>();
		totalBuildNamesAndCount.clear();
		for (Jenkin sr : totalBuildNamesList) {
			totalBuildNamesAndCount.put(sr.getJob(), sr.getCount());
		}
		jenkinForm.setTotalBuildCountAndNames(totalBuildNamesAndCount);

		//failed build names and count list
		List<Jenkin> failedBuildNamesList =jenkinsService.getFailedBuildNamesAndCount();
		LinkedHashMap<String, Integer> failedBuildNamesAndCount = new LinkedHashMap<String, Integer>();
		for (Jenkin sr : failedBuildNamesList) {
			failedBuildNamesAndCount.put(sr.getJob(), sr.getCount());
		}
		jenkinForm.setFailedBuildCountAndNames(failedBuildNamesAndCount);
		//success build names and count list
		List<Jenkin> successBuildNamesList =jenkinsService.getSuccessBuildNamesAndCount();
		LinkedHashMap<String, Integer> successBuildNamesAndCount = new LinkedHashMap<String, Integer>();
		successBuildNamesAndCount.clear();
		for (Jenkin sr : successBuildNamesList) {
			successBuildNamesAndCount.put(sr.getJob(), sr.getCount());
		}
		jenkinForm.setSuccessBuildCountAndNames(successBuildNamesAndCount);

		//aborted build names and count list
		List<Jenkin> abortedBuildNamesList =jenkinsService.getAbortedBuildNamesAndCount();
		LinkedHashMap<String, Integer> abortedBuildNamesAndCount = new LinkedHashMap<String, Integer>();
		abortedBuildNamesAndCount.clear();
		for (Jenkin sr : abortedBuildNamesList) {
			abortedBuildNamesAndCount.put(sr.getJob(), sr.getCount());
		}
		jenkinForm.setAbortedBuildCountAndNames(abortedBuildNamesAndCount);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<JenkinFrom>(jenkinForm,headers,HttpStatus.OK);
	*/
		return null;
		}

	
	/**
	 * @param jobName
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * 
	 * this function is for running jenkins server job from java code
	 */
	@RequestMapping(value = "/jobrun/{jobName}", method = RequestMethod.GET)
	public void individualJobRun(@PathVariable("jobName") String jobName) throws ParseException, IOException{
		try {
			jenkinsUtilService.runJenkinsJob(databasePropertyUtils.jenkinHost, databasePropertyUtils.jenkinPort,databasePropertyUtils.proxyIpAddress,databasePropertyUtils.proxyPort,databasePropertyUtils.proxyScheme,databasePropertyUtils.targetScheme,jobName);
		} catch (ParserConfigurationException | SAXException e) {
			logger.error("ParserConfigurationException | SAXException --- > "+e.getMessage());
			e.printStackTrace();
		}
	}



	/**
	 * @throws ParseException
	 * 
	 * create new job in jenkins 
	 */
	@RequestMapping(value = "/jenkinscreatejob", method = RequestMethod.GET)
	public void createJob() throws ParseException{
		String jobName = "TestJob_ZICOS"; 
		String userName = "admin"; // username
		String password = "admin"; // password or API token
		String createJenkinJob = jenkinsUtilService.createJob(databasePropertyUtils.jenkinUrl, jobName, "<project><builders><hudson.tasks.BatchFile><command>@ECHO OFF&#xA;ECHO Line 1&#xA;ECHO Line 2</command></hudson.tasks.BatchFile></builders><publishers/><buildWrappers/></project>", userName, password);
		logger.info("created jenkins job ::::: "+jobName );
	}			

	/*Loading environments..*/
	@RequestMapping(value="/loadallenvironments/{projId}",method=RequestMethod.GET)
	public ResponseEntity<List<Environment>> loadAllEnvironments(@PathVariable("projId") Long projId){
		logger.info("Loading Environments...");
		HttpHeaders headers = new HttpHeaders();
		Environment env = new Environment();
		List<Environment> environmentsLst = envService.loadAllEnvironments(projId); 
		return new ResponseEntity<List<Environment>>(environmentsLst,headers, HttpStatus.OK);
	}
	
	/*Loading pipeline def..*/
	@RequestMapping(value="/loadallpipedef/{projId}/{envId}",method=RequestMethod.GET)
	public List<PipelineDef> loadAllPipeDef(@PathVariable("projId") Long projId,@PathVariable("envId") Long envId){
		logger.info("Loading pipeline def...");
		HttpHeaders headers = new HttpHeaders();
		Environment env = new Environment();
		List<PipelineDef> pipeLineDefList = pipelineDefService.loadAllPipelineDef(projId,envId); 
		return pipeLineDefList;
	}
	
}
