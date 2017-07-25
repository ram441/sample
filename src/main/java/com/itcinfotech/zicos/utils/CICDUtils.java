package com.itcinfotech.zicos.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Encoder;


import com.itcinfotech.zicos.sql.model.DevopTool;
import com.itcinfotech.zicos.sql.model.Tools;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Component
public class CICDUtils {
	static final Logger logger = LogManager.getLogger(CICDUtils.class.getName());
	
	@Value("${proxy.enable}")
	private boolean proxyEnable;
	
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
	
	/**
	 * @param host
	 * @param port
	 * @param path
	 * @param proxyIpAddress
	 * @param proxyPort
	 * @param flag 
	 * @param proxyScheme
	 * @param targetScheme
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public boolean getTestConnResponse(String urlPath,String proxyIP,Integer proxyPort,String userName,String pwd, boolean flag,String toolName){
		boolean connFlag =false;
		URL url = null;
		HttpURLConnection conn = null;
		StringBuffer  output = new StringBuffer();
		String line = null;
		if(toolName!= null && StringUtils.isNotEmpty(toolName)){
			
			if(toolName.equalsIgnoreCase(Constants.JIRA_TOOL) || toolName.equalsIgnoreCase(Constants.BITBUCKET_TOOL) || toolName.equalsIgnoreCase(Constants.CHEF_TOOL)){
				
				try {
					url = new URL(urlPath);
					if(proxyEnable) {
						Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP, proxyPort));
						conn = (HttpURLConnection) url.openConnection(proxy);
					}else {
						conn = (HttpURLConnection) url.openConnection();
					}
					if(flag){
						String userpass = userName + ":" +pwd;
						String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
						conn.setRequestProperty ("Authorization", basicAuth.replaceAll("\n",""));
					}
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					if (conn.getResponseCode() != 200) {
						logger.error("Failed : HTTP error code : " + conn.getResponseCode());
						connFlag=false;
						//return new StringBuffer("Failed : HTTP error code : "+conn.getResponseCode());
						return connFlag;
						
					}
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					logger.info("Output from Server .... \n");
					while ((line = br.readLine()) != null) {
						output = output.append(line);
						connFlag=true;
					}
				} catch (IOException e) {
					logger.error("Error while requesting jira response : "+e.getMessage());
				}finally{
					if(conn != null){
						conn.disconnect();
						line = null;
					}
				}
				
			}else if(toolName.equalsIgnoreCase(Constants.JENKINS_TOOL) || toolName.equalsIgnoreCase(Constants.SONAR_TOOL) || toolName.equalsIgnoreCase(Constants.NEXUS_TOOL)){
				 	String authString = userName + ":" + pwd;
			        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			        logger.info("Base64 encoded auth string: " + authStringEnc);
			        Client restClient = Client.create();
			        WebResource webResource = restClient.resource(urlPath);
			        ClientResponse resp = null;
			        try{
			         resp = webResource.accept("application/json")
			                                         .header("Authorization", "Basic " + authStringEnc)
			                                         .get(ClientResponse.class);
			        }catch(Exception e){
			        	logger.info("Jenkings Url error");
			        	 connFlag = false;
			        }
			        if(resp!= null && resp.getStatus() == 200){
			        	logger.info("Jenkings connect to the server");
			            connFlag = true;
			        }
			        
			}else{
				 connFlag= false;
			}
		
			
		}
		return connFlag;
	}
	
	/**
	 * 
	 * @param str_Host
	 * @param str_Username
	 * @param str_Password
	 * @param int_Port
	 * @param str_FileDirectory
	 * @param str_FileName
	 * @return
	 */
	public boolean fetchToolConfigDtsByShellScriptCall(Long projectId,List<Tools> formTools)  {
		System.out.println(linuxHostName+"-linuxUserName-"+linuxUserName+" -linuxPassword- "+linuxPassword+"-linuxPort--"+linuxPort+"-shellScriptCmd-"+shellScriptCmd+"-fetchTextDtlsCmd-"+fetchTextDtlsCmd);
		
		 boolean flag =false;
		  JSch jsch = new JSch();
	      Session session = null;
	      String toolName = new String();
	  	  if(formTools!= null && formTools.size()>0){
		  		for(Tools tb:formTools){
		  			if(tb.getToolName()!=null && StringUtils.isNotEmpty(tb.getToolName())){
		  				toolName+=" "+tb.getToolName().toLowerCase();
		  			}
		  		}
	  		}
	  	  logger.info(toolName);
	  	 // shellScriptCmd=shellScriptCmd.trim()+" "+projectId+toolName;
	  	 shellScriptCmd=shellScriptCmd.trim()+toolName;
	      logger.info("Trying to connect..and execute...shellScriptCmd:"+shellScriptCmd);
	      try{
		    	
		    	java.util.Properties config = new java.util.Properties(); 
		    	config.put("StrictHostKeyChecking", "no");
		    	session=jsch.getSession(linuxUserName, linuxHostName,linuxPort);
		    	session.setPassword(linuxPassword);
		    	session.setConfig(config);
		    	session.connect();
		    	logger.info("Connected");
		    	Channel channel=session.openChannel("exec");
		       ((ChannelExec)channel).setCommand(shellScriptCmd);
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        channel.connect();
		        channel.disconnect();
		        session.disconnect();
		        flag=true;
		        logger.info("END");
		    }catch(Exception e){
		    	e.printStackTrace();
		    	flag=false;
		    }
	      return  flag;//ltDevToolPojo;
	  }
	
	
	}
