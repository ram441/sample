package com.itcinfotech.zicos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.itcinfotech.zicos.nosql.model.Jenkin;
import com.itcinfotech.zicos.nosql.service.JenkinsService;
import com.itcinfotech.zicos.pipeline.service.PipeLineService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class JenkinUtils {
	@Autowired
	ConstantPropertiesUtils databasePropertyUtils;
	@Autowired
	PipeLineService pipelineService;

	static final Logger logger = LogManager.getLogger(JenkinUtils.class.getName());
	//------------------------------------- Jenkin
	/**
	 * @param jenkinUrl
	 * @param proxyIp
	 * @param proxyPort
	 * @return
	 * generic function to return Json response from jenkin api.
	 */
	public StringBuffer getJenkinResponse(String jenkinUrl,String proxyIP,Integer proxyPort){
		URL url = null;
		HttpURLConnection conn = null;
		StringBuffer  output = new StringBuffer();
		String line = null;
		try {
			url = new URL(jenkinUrl);
			
			String authStr = databasePropertyUtils.jenkinsusername +":"+  databasePropertyUtils.jenkinspassword;
		    String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));
		    if(databasePropertyUtils.proxyEnable) {
		    	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP, proxyPort));
		    	conn = (HttpURLConnection) url.openConnection(proxy);
		    }else {
		    	conn = (HttpURLConnection) url.openConnection();
		    }
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				logger.error("Failed : HTTP error code : " + conn.getResponseCode());
				return new StringBuffer("Failed : HTTP error code : "+conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			logger.info("Output from Server .... \n");
			while ((line = br.readLine()) != null) {
				output = output.append(line);
			}
		} catch (IOException e) {
			logger.error("Error while requesting jira response : "+e.getMessage());
		}finally{
			if(conn != null){
				conn.disconnect();
				line = null;
			}
		}
		return output;
	}


	public List<String> getAllViewNames(String jenkinUrl,JenkinsService jenkinsService,String proxyIpAddress,Integer proxyPort){
		List<String> viewsProjList= new ArrayList<String>();
		Object jenkinResponse = getJenkinResponse(jenkinUrl+Constants.viewUrl, proxyIpAddress, proxyPort);
		System.out.println(jenkinResponse);
		JSONObject jsonObject = null;
		if(jenkinResponse != null){
			try {
				jsonObject = new JSONObject(jenkinResponse.toString());
				JSONArray views = jsonObject.getJSONArray("views");
				for (int i=0; i<views.length(); i++) {
					JSONObject buildsArr = views.getJSONObject(i);
					String viewName = buildsArr.getString("name");
					viewsProjList.add(viewName);
					logger.info("All View Names in Jenkins ::: "+viewName);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return viewsProjList;
	}


	public Set<String> extractJobsFromViewProject(String jenkinUrl,JenkinsService jenkinsService,String proxyIpAddress,Integer proxyPort,String projectName){
		Set<String> jobs = new HashSet<String>();
		//get all view project names from direct jenkins server
		List<String> viewProjNames = getAllViewNames(jenkinUrl,jenkinsService,proxyIpAddress,proxyPort);
		for(String projName : viewProjNames){
			logger.info("view project names : "+projName);
			String viewName = projName;
			if(viewName.equals(projectName)){
				Object jenkinResponse = getJenkinResponse(jenkinUrl+Constants.view+viewName+Constants.filterUrl, proxyIpAddress, proxyPort);
				logger.info("json response for jobs associated with view project : "+jenkinResponse);
				JSONObject jsonObject = null;
				if(jenkinResponse != null){
					try {
						jsonObject = new JSONObject(jenkinResponse.toString());
						JSONArray builds = jsonObject.getJSONArray("jobs");
						for (int i=0; i<builds.length(); i++) {
							JSONObject buildsArr = builds.getJSONObject(i);
							String jobName = buildsArr.getString("name");
							jobs.add(jobName);
							logger.info("All Jobs ::: "+jobName);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return jobs;
	}
	/**
	 * @param jenkinUrl
	 * @param jenkinsService
	 * @param proxyIpAddress
	 * @param proxyPort
	 * @return
	 * 
	 * this function is to extract all jenkin jobs for dynamically pass job as parameter and using in  createJenkinsJsonData function  
	 */
	private List<String> extractJobsFromJenkinApiResponse(String jenkinUrl,JenkinsService jenkinsService,String proxyIpAddress,Integer proxyPort){
		List<String> jobs = new ArrayList<String>();
		Object jenkinResponse = getJenkinResponse(jenkinUrl+Constants.jobApiJsonUrl, proxyIpAddress, proxyPort);
		System.out.println(jenkinResponse);
		JSONObject jsonObject = null;
		if(jenkinResponse != null){
			try {
				jsonObject = new JSONObject(jenkinResponse.toString());
				JSONArray builds = jsonObject.getJSONArray("jobs");
				for (int i=0; i<builds.length(); i++) {
					JSONObject buildsArr = builds.getJSONObject(i);
					String jobName = buildsArr.getString("name");
					jobs.add(jobName);
					logger.info("All Jobs ::: "+jobName);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jobs;
	}

	public String identifyFirstJobForPipeline(String jenkinUrl,JenkinsService jenkinsService,String proxyIpAddress,Integer proxyPort,String jobName){
		String firstJob = null;
		JSONObject jsonObject = null;
		Integer upStreamProjectsSize = null;
		Object jenkinResponse = getJenkinResponse(jenkinUrl+Constants.jobUrl+jobName+Constants.firstJobUrl, proxyIpAddress, proxyPort);
		logger.info("jenkinResponse : "+jenkinResponse);
		if(jenkinResponse != null){
			try {
				jsonObject = new JSONObject(jenkinResponse.toString());
				String displayName = jsonObject.getString("displayName");
				String jenkinsJobName = jsonObject.getString("name");
				logger.info("All Jobs ::: "+jenkinsJobName);
				JSONArray upstreamProjects = jsonObject.getJSONArray("upstreamProjects");
				upStreamProjectsSize = upstreamProjects.length(); 
				logger.info("upstreamProjects length : "+upStreamProjectsSize);
				if(upStreamProjectsSize == null || upStreamProjectsSize == 0){
					firstJob = jobName;
					return firstJob;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return firstJob;
	}

	public boolean isPipeRunning(Long pipeDefId){
		return pipelineService.isPipeRunning(pipeDefId);
	}
	
	/**
	 * @param jenkinUrl
	 * @param jenkinsService
	 * @param proxyIpAddress
	 * @param proxyPort
	 * @return
	 * 
	 * this function is to modify URL as per requirement and setting values to jenkin domain class which will be used in jenkinController class to store data in to Mongo
	 */
	public List<Jenkin> createJenkinsJsonData(String jenkinUrl,JenkinsService jenkinsService,String proxyIpAddress,Integer proxyPort){
		List<String> jobList = extractJobsFromJenkinApiResponse(jenkinUrl, jenkinsService, proxyIpAddress, proxyPort);
		List<Jenkin> jenkin= new ArrayList<Jenkin>();
		Jenkin jenkinDomain= null;
		JSONObject jsonObject = null;
		for(String job: jobList){
			logger.info("total url :"+jenkinUrl+Constants.jobUrl+job+Constants.jenkinsUrlPath);
			if(job.indexOf(" ") != -1){
				job = new String(job.toString().replaceAll(" ", "%20"));
			}
			logger.info("job name after adde %20: "+job);
			Object jenkinResponse = getJenkinResponse(jenkinUrl+Constants.jobUrl+job+Constants.jenkinsUrlPath, proxyIpAddress, proxyPort);
			logger.info("Jenkin Response : "+jenkinResponse);
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			if(jenkinResponse != null){
				try {
					jsonObject = new JSONObject(jenkinResponse.toString());

					JSONArray builds = jsonObject.getJSONArray("builds");
					for (int i=0; i<builds.length(); i++) { // start iterating builds array
						jenkinDomain = new Jenkin();
						JSONObject buildsArr = builds.getJSONObject(i);

						String displayName = jsonObject.getString("displayName");
						logger.info("fullDisplayName : "+displayName);
						jenkinDomain.setJob(displayName);

						String name =jsonObject.getString("name");
						jenkinDomain.setBuildName(name);
						logger.info("build name : "+name);
						
						String buildId = buildsArr.getString("id");
						jenkinDomain.setBuildId(buildId);
						logger.info("id :"+buildId);

						jenkinDomain.setJob(job);
						logger.info("job name : "+job);

						String buildNumber = buildsArr.getString("number");
						jenkinDomain.setBuildNumber(Integer.parseInt(buildNumber));
						logger.info("Build Number :"+buildNumber);

						Integer buildDuration = Integer.parseInt(buildsArr.getString("duration"));
						jenkinDomain.setBuildDuration(buildDuration);
						logger.info("Build duration : "+buildDuration);

						String timestamp =buildsArr.getString("timestamp");
						jenkinDomain.setTimestamp(timestamp);
						logger.info("timestamp : "+timestamp);
						
						String result =buildsArr.getString("result");
						jenkinDomain.setResult(result);
						logger.info("result : "+result);

						if(jsonObject.get("lastBuild") instanceof JSONObject && jsonObject.getJSONObject("lastBuild") != null){
							JSONObject lastBuild = jsonObject.getJSONObject("lastBuild");
							Integer lastBuildDuration = Integer.parseInt(lastBuild.getString("duration"));
							jenkinDomain.setLastBuildDuration(lastBuildDuration.toString());
							logger.info("lastBuild duration : "+lastBuildDuration);
						}
						if(jsonObject.get("lastSuccessfulBuild") instanceof JSONObject && jsonObject.getJSONObject("lastSuccessfulBuild") != null){
							JSONObject lastSuccessfulBuild = jsonObject.getJSONObject("lastSuccessfulBuild");
							Integer lastSuccessfulBuildDuration = Integer.parseInt(lastSuccessfulBuild.getString("duration"));
							jenkinDomain.setLastSuccessBuildDuration(lastSuccessfulBuildDuration.toString());
							logger.info("lastSuccessfulBuild duration : "+lastSuccessfulBuildDuration);
						}
						if(jsonObject.get("lastUnsuccessfulBuild") instanceof JSONObject && jsonObject.getJSONObject("lastUnsuccessfulBuild") != null){
							JSONObject lastUnSuccessfulBuild = jsonObject.getJSONObject("lastUnsuccessfulBuild");
							Integer lastUnSuccessfulBuildDuration = Integer.parseInt(lastUnSuccessfulBuild.getString("duration"));
							jenkinDomain.setLastUnSuccessBuildDuration(lastUnSuccessfulBuildDuration.toString());
							logger.info("lastUnsuccessfulBuild duration : "+lastUnSuccessfulBuildDuration);
						}
						if(jsonObject.get("downstreamProjects") instanceof JSONObject && jsonObject.getJSONObject("downstreamProjects") != null){
							JSONObject downstreamProjects = jsonObject.getJSONObject("downstreamProjects");
							Integer downStreamProjectsSize = downstreamProjects.length(); 
							logger.info("downstreamProjects length : "+downStreamProjectsSize);
						}
						if(jsonObject.get("upstreamProjects") instanceof JSONObject && jsonObject.getJSONObject("upstreamProjects") != null){
							JSONObject upstreamProjects = jsonObject.getJSONObject("upstreamProjects");
							Integer upStreamProjectsSize = upstreamProjects.length(); 
							logger.info("upstreamProjects length : "+upStreamProjectsSize);
						}
						jenkin.add(jenkinDomain);

						/*Date timestamp = null;
						if(buildsArr.has("timestamp")  && buildsArr.getString("timestamp") != null && !buildsArr.getString("timestamp").equals("null")){
							try {
								timestamp = formatter.parse((String) buildsArr.getString("timestamp"));
								logger.info("timestamp ::: "+timestamp);
							} catch (ParseException ex) {
								ex.printStackTrace();
							}
							jenkinDomain.setTimestamp(timestamp);
						}*/
					}// end for loop iterating builds array

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		logger.info("jenkins size in jenkin util class : "+jenkin);
		return jenkin;
	}
	
	public Map<String, Map<String, Map<String,String>>> getPipeStatusDtls(String jenkinUrl,String proxyIpAddress,Integer proxyPort,String projectName){
		Map<String, Map<String, Map<String,String>>> pipeDtlsMap = new HashMap<String,Map<String, Map<String,String>>>();
		Map<String, Map<String,String>> allJobsMap = new HashMap<String,Map<String,String>>();
		Map<String, Map<String,String>> runningJobsMap = new HashMap<String,Map<String,String>>();
		String filterUrl = Constants.view+projectName+Constants.pipelineFilterUrl;
		Object jenkinResponse = getJenkinResponse(jenkinUrl+filterUrl, proxyIpAddress, proxyPort);
		JSONObject jsonObject = null;
		if(jenkinResponse != null){
			String jobName, color, buildID, estimatedDuration, result, duration, building;
			try {
				jsonObject = new JSONObject(jenkinResponse.toString());
				JSONArray jobArray = jsonObject.getJSONArray("jobs");
				for (int i=0; i<jobArray.length(); i++) {
					Map<String,String> buildDtlsMap = new HashMap<String, String>();
					jobName = jobArray.getJSONObject(i).getString("name");
					color = jobArray.getJSONObject(i).getString("color");
					JSONArray buildsArr = jobArray.getJSONObject(i).getJSONArray("builds");
					//capture latest build details of the selected Job
					building = buildsArr.getJSONObject(0).getString("building");
					buildID = buildsArr.getJSONObject(0).getString("id");
					estimatedDuration = buildsArr.getJSONObject(0).getString("estimatedDuration");
					result = buildsArr.getJSONObject(0).getString("result");
					duration = buildsArr.getJSONObject(0).getString("duration");
					buildDtlsMap.put("buildID", buildID);
					buildDtlsMap.put("estimatedDuration", estimatedDuration);
					buildDtlsMap.put("result", result);
					buildDtlsMap.put("duration", duration);
					//If building=true, it is a running job
					if(building.equalsIgnoreCase("true"))
					{
						allJobsMap.put(jobName, buildDtlsMap);
						runningJobsMap.put(jobName, buildDtlsMap);
					}else {
						allJobsMap.put(jobName, buildDtlsMap);
					}
				}
				pipeDtlsMap.put("allJobsMap", allJobsMap);
				pipeDtlsMap.put("runningJobsMap", runningJobsMap);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return pipeDtlsMap;
	}
	public String getDurationBreakdown(long millis) {
	    if(millis < 0) {
	      throw new IllegalArgumentException("Duration must be greater than zero!");
	    }

	 /*   long days = TimeUnit.MILLISECONDS.toDays(millis);
	    millis -= TimeUnit.DAYS.toMillis(days);*/
	    long hours = TimeUnit.MILLISECONDS.toHours(millis);
	    millis -= TimeUnit.HOURS.toMillis(hours);
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
	    millis -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

	    StringBuilder sb = new StringBuilder(64);
	 /*   sb.append(days);
	    sb.append(" Days ");*/
	    sb.append(hours);
	    sb.append(" H ");
	    sb.append(minutes);
	    sb.append(" M ");
	    sb.append(seconds);
	    sb.append(" S ");

	    return sb.toString();
	}
	public String milliToDate(Long timeStamp){
		 Date date = new Date(timeStamp);
		return DateFormat.getDateInstance().format(date).toString();
	}
	

	/**
	 * function is used for calculate failed rate
	 * used in jenkinJsonController
	 * @param failureCount
	 * @param totalCount
	 * @return
	 */
	public Double getFailureRate(int failureCount, int totalCount){
		double fCount = failureCount;
		double tCount = totalCount;		
		double rate = Math.round((fCount/tCount)*100);
		return rate;
	}	
	
	
	/**
	 * @param host
	 * @param port
	 * @param proxyIpAddress
	 * @param proxyPort
	 * @param proxyScheme
	 * @param targetScheme
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * 
	 * this function is to trigger job from java
	 */
	public boolean runJenkinsJob(String host, Integer port,String proxyIpAddress,Integer proxyPort,String proxyScheme,String targetScheme, String jobName) 
			throws ClientProtocolException, IOException, ParserConfigurationException, SAXException{
		try {
			String authStr = databasePropertyUtils.jenkinsusername +":"+  databasePropertyUtils.jenkinspassword;
			HttpURLConnection connection = null;
			String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));

			URL url = new URL (databasePropertyUtils.jenkinUrl+"/job/"+jobName+"/build");
			if(databasePropertyUtils.proxyEnable) {
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIpAddress, proxyPort));
				connection = (HttpURLConnection) url.openConnection(proxy);
			}else {
				connection = (HttpURLConnection) url.openConnection();
			}
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = connection.getInputStream();
			logger.info(jobName+" build has been done successfully..");
			BufferedReader in   =
					new BufferedReader (new InputStreamReader (content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * @param url
	 * @param jobName
	 * @param xml
	 * @param userName
	 * @param password
	 * @return
	 * 
	 * this function to create new job in jenkins 
	 */
	public String createJob(String url, String jobName, String xml , String userName, String password){
		Client client = Client.create();
		client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter(userName, password));
		WebResource webResource = client.resource(url+"/createItem?name="+jobName);
		ClientResponse response = webResource.type("application/xml").post(ClientResponse.class, xml);
		String jsonResponse = response.getEntity(String.class);
		client.destroy();
		logger.info("Response createJob  :::: "+jsonResponse.toString());
		return jsonResponse;
	}
}