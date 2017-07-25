package com.itcinfotech.zicos.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itcinfotech.zicos.nosql.service.JenkinsService;
import com.itcinfotech.zicos.pipeline.model.JobsPipeline;
import com.itcinfotech.zicos.pipeline.model.PipeLineTxn;
import com.itcinfotech.zicos.pipeline.model.PipelineDef;
import com.itcinfotech.zicos.pipeline.service.PipeLineService;
import com.itcinfotech.zicos.pipeline.service.PipeLineTxnService;
import com.itcinfotech.zicos.pipeline.service.PipelineDefService;

@Component
public class PipeStateUpdater {
	@Autowired
	private JenkinsService jenkinsService;
	@Autowired
	private PipeLineTxnService pipeLineTxnService;
	@Autowired
	private PipelineDefService pipelineDefService;
	@Autowired
	private PipeLineService jobsPipeLineService;
	@Autowired
	private JenkinUtils jenkinUtils;
	@Autowired
	private ConstantPropertiesUtils databasePropertyUtils;
	
	
	/*@Value("${jenkin.url}")
	public String jenkinUrl;

	@Value("${proxy.ip_address}")
	public String proxyIpAddress;

	@Value("${proxy.port}")
	public Integer proxyPort;*/

	
	static final Logger log = LogManager.getLogger(PipeStateUpdater.class.getName());
	
	public PipeStateUpdater(){}
	
	public void pipeStatusManager(Map<String, Map<String, Map<String,String>>> pipeDtls) {
		 
		
	}
	public void monitorPipe(final Map<String, Map<String, Map<String,String>>> initialStateMap, final String projName,final JobsPipeline jobsPipeline,final PipelineDef pipelineDef) {
		final Timer pipelineTimer = new Timer();
		long pipeInterval = 20000L;
		final Map<String, Map<String,String>> updatedJobsMap = new HashMap<>();
		final Set<String> executedJobs = new HashSet<>();
		pipelineTimer.scheduleAtFixedRate(new TimerTask() {
            int freeRunCounter = 0;
			@Override
			public void run() {
				Map<String, Map<String, Map<String,String>>> currrentStateMap = jenkinUtils.getPipeStatusDtls(databasePropertyUtils.jenkinUrl,databasePropertyUtils.proxyIpAddress,databasePropertyUtils.proxyPort,projName);
				
				final Map<String, Map<String,String>> runningJobsMap = currrentStateMap.get("runningJobsMap");
				Set<String> runningJobNameList = runningJobsMap.keySet();
				
				if(null == runningJobNameList || runningJobNameList.isEmpty()) {
					//Three possibilities to come here.
					/*1. All jobs completed.
					  2. One of the job failed and pipe is broken.
					  3. May be some jobs in waiting state*/
					//TO DO Compare initialStateMap with currrentStateMap to handle 1 and 2 above.
					// May be some jobs in waiting state I will check for 3 iteration(condition 3 above)
					
				//	String pipeRunningStatus = compareIntialMapAndCrntMap(initialStateMap,currrentStateMap);
					/*if(pipeRunningStatus.equals("COMPLETED")){
						pipelineDef.setCurrentStatus("Completed");
						pipelineTimer.cancel();
					}*/
					
					if(freeRunCounter<=3) {
						freeRunCounter++;
					}
					else {
						//updating running state to 'Completed' in pipe_def table
						// currrentStateMap = jenkinUtils.getPipeStatusDtls(jenkinUrl,proxyIpAddress,proxyPort,projName);
						 Map<String, Map<String,String>> allLatestJobsMap = currrentStateMap.get("allJobsMap");
						 PipelineDef pipeDef = pipelineDefService.findPipelineDefByProjectName(projName);
						//	PipeLineTxn pipeTxn=  pipeLineTxnService.fetchTxnId(runningjob,pipeExecId.getPipeExecId());
						pipelineDef.setCurrentStatus("Completed");
						pipelineDefService.updatePipelineDef(pipelineDef);
						//TO DO update pipeline_txn table
						//TO DO  Need to prepare list of pipeLineTnx form beans and update each form bean with a respective jobs from allLatestJobsMap to update buildId and status for all jobs
						List<PipeLineTxn> pipeTxnFormBeanlist =  pipeLineTxnService.getPipeTxnByExecIdAndPipeDef(pipeDef.getPipeExecId(),pipeDef.getPipelineDefId());
						List<PipeLineTxn> updatedPipeLineTxnLst = updateTxnBeanWithLatestStatus(allLatestJobsMap,pipeTxnFormBeanlist);
						for(PipeLineTxn txn : updatedPipeLineTxnLst) {
							log.info("updatedpipeline trasaction in pipe run thread : "+txn); 
						}
						pipeLineTxnService.updatePipeLineTxn(updatedPipeLineTxnLst);
						log.info("initialStateMap : "+initialStateMap);
						log.info(" updatedPipeLineTxnLst : "+updatedPipeLineTxnLst);
						pipelineTimer.cancel();
					}
				}
				else {
					freeRunCounter = 0;
					for(final String runningjob: runningJobNameList) {
						if(!executedJobs.contains(runningjob))
						{
							executedJobs.add(runningjob);
							// running executor thread
							int interval = Integer.valueOf(runningJobsMap.get(runningjob).get("estimatedDuration"))/4;
							log.info("Interval is "+interval);
							final Timer jobTimer = new Timer();
							jobTimer.scheduleAtFixedRate(new TimerTask() {

								@Override
								public void run() {
									// Do your task
									String status = getJobStatus(runningjob, runningJobsMap.get(runningjob).get("buildID"));
									if(status.equals("null")) 
										log.info("Job "+runningjob+" is still running..");
									else{
										log.info("Job "+runningjob+" is done with status : "+status);
										PipelineDef pipeDef = pipelineDefService.findPipelineDefByProjectName(projName);
										// TO DO update based on pipeExecutionId, jobName,pipeDefId
										PipeLineTxn pipeTxn=  pipeLineTxnService.getPipeTxnByRunnigJobsAndExecIdAndDefId(runningjob,pipeDef.getPipeExecId(),pipeDef.getPipelineDefId());
										pipeTxn.setPipelineTxnId(pipeTxn.getPipelineTxnId());
										pipeTxn.setIsAssigned(status);
										Long buildId =Long.parseLong(runningJobsMap.get(runningjob).get("buildID"));
										pipeTxn.setBuildId(buildId);
										pipeLineTxnService.updatePipeLineTxn(pipeTxn);
										log.info("update pipeline_def set status='"+status+"', build_id='"+runningJobsMap.get(runningjob).get("buildID")+"' where job_name='"+runningjob+"'");
										jobTimer.cancel();
									}
								}
							}, 0, interval);
						
						}
						else {
						}
					}
				}
			}
		}, 0, pipeInterval);
	}
	//pipeline status from  
	public String getJobStatus(String jobName, String buildId) {
		String filterUrl = "/job/"+jobName+"/"+buildId+"/api/json?tree=id,result";
		log.info("filter url : "+filterUrl);
		Object jenkinResponse = jenkinUtils.getJenkinResponse(databasePropertyUtils.jenkinUrl+filterUrl, databasePropertyUtils.proxyIpAddress, databasePropertyUtils.proxyPort);
		log.info("Jenkins response is : "+jenkinResponse);
		JSONObject jsonObject = null;
		String status = null;
		if(jenkinResponse != null){
			try {
				jsonObject = new JSONObject(jenkinResponse.toString());
				status = jsonObject.getString("result");
				if(status == null){
					return "Running";
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	protected List<PipeLineTxn> updateTxnBeanWithLatestStatus(Map<String, Map<String, String>> allLatestJobsMap,List<PipeLineTxn> pipeTxnFormBeanlist) {
		log.info("allLatestJobsMap map in updateTxnBean function : "+allLatestJobsMap);
		for(PipeLineTxn pipeTxn : pipeTxnFormBeanlist){
			String jobsName = pipeTxn.getJobsPipeline().getJobName();
			log.info("jobsName in updateTxnBeanWithLatestStatus function : "+jobsName);
			pipeTxn.setPipelineTxnId(pipeTxn.getPipelineTxnId());
			Long buildId =Long.parseLong(allLatestJobsMap.get(jobsName).get("buildID"));
			log.info("Build id in updateTxnBeanWithLatestStatus function : "+buildId);
			String status =allLatestJobsMap.get(jobsName).get("result");
			pipeTxn.setBuildId(buildId);
			pipeTxn.setIsAssigned(status);
		}
	return pipeTxnFormBeanlist;	
	}
	
	protected String compareIntialMapAndCrntMap(Map<String, Map<String, Map<String,String>>> initialStateMap, Map<String, Map<String, Map<String,String>>> currrentStateMap){
		Map<String, Map<String,String>> initialMap = initialStateMap.get("allJobsMap");
		Map<String, Map<String,String>> currentMap = currrentStateMap.get("allJobsMap");
		String tempJobStatus = null;
		int counter = 0;
		for(String jobName : initialMap.keySet()){
				Map<String, String> initialMapBuildState = initialMap.get(jobName);
				Map<String,String> curretnMapBuildState = currentMap.get(jobName);
				if(Long.parseLong(initialMapBuildState.get("buildID")) < Long.parseLong(curretnMapBuildState.get("buildID"))){
					tempJobStatus = curretnMapBuildState.get("result");
					counter++;
				}else{
					if(!tempJobStatus.equalsIgnoreCase("SUCCESS")){
						return "COMPLETED";
					}
					return "RUNNING";
				}
				if(counter == initialMap.size()){
				return "COMPLETED";
				}
		}
		return "RUNNING";
	}
	
}

