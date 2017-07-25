package com.itcinfotech.zicos.utils;

public interface Constants {

	public static final String IS_LDAP_USER = "ldap user";
	public static final String IS_ZICOS_USER = "zicos user";
	public static final String IS_LDAP_AND_ZICOS_USER = "ldap and zicos";
	
	public static final String DEFAULT_USER_PASSWORD = "c7a85972e188c669f0d1e24528a772a3"; // it is in md5passwordencoder
	public static final String SERVICE_CHANGE_PASSWORD = "Reset Password Automatic mail";
	public static final String INVALID_USER = "Invalid User";
	public static final String INVALID_PASSWORD = "Invalid Password";
	public static final String VALID_USER_AND_PASSWORD = "Valid User Name and Password";
	
	public static final String BITBUCKET_TOOL="Bitbucket";
	public static final String CHEF_TOOL="Chef";
	public static final String JENKINS_TOOL="Jenkins";
	public static final String JIRA_TOOL="Jira";
	public static final String NEXUS_TOOL="Nexus";
	public static final String SONAR_TOOL="Sonar";
	public static final String REDMINE_TOOL="Redmine";
	
	public static final String IN_PROGRESS = "In Progress";
	public static final String SUCCESS = "Success";
	public static final String PEDING = "Pending";
	
	
	public static final String TOOL_NAME="toolName";
	public static final String URL="url";
	public static final String USERNAME="userName";
	public static final String PASSWORD="password";
	public static final String ACCESSKEY="accessKey";
	public static final String SECUREKEY="secureKey";
	public static final String PIPE_SYMBOL="||";
	public static final String HASH_SYMBOL="##";
	
	public static final Character ACTIVE_FLAG='Y';
	public static final Character INACTIVE_FLAG='N';
	public static final String SUCCESS_MSG="SUCCESS";
	public static final String FAILED_MSG="FAILED";
	public static final String JIRA_URLPATH="/rest/api/latest/project";
	public static final String CICD_SUBJECT="New Tools Configuration";
	public static final String SPACE=" ";
	public static final String COMMA=",";
	
	
	//jenkins utils 
	public static final String jenkinsUrlPath="/api/json?tree=name,displayName,builds[number,timestamp,duration,id,fullDisplayName,result,actions[causes[userID,shortDescription],lastBuiltRevision[SHA1],remoteUrls]],lastBuild[estimatedDuration,result,duration,number,timestamp,actions[causes[userName]]],lastUnsuccessfulBuild[estimatedDuration,result,duration,number,timestamp],lastSuccessfulBuild[estimatedDuration,result,duration,number,timestamp,actions[causes[userName]]],upstreamProjects,downstreamProjects";
	public static final String jobUrl = "/job/";
	public static final String jobApiJsonUrl = "/api/json?tree=jobs[name,displayName]";
	public static final String firstJobUrl = "/api/json?tree=name,displayName,upstreamProjects,downstreamProjects";
	public static final String viewUrl = "/api/json/view?tree=views[name]";
	public static final String filterUrl = "/api/json?tree=jobs[name,displayName]";
	public static final String view = "/view/";
	public static final String pipelineFilterUrl = "/api/json?tree=jobs[name,color,builds[id,result,estimatedDuration,duration,building]{0}]"; 
	
	

}
