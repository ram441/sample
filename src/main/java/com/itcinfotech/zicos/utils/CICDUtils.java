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


public class CICDUtils {
	static final Logger logger = LogManager.getLogger(CICDUtils.class.getName());
	
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
	public static boolean getTestConnResponse(String urlPath,String proxyIP,Integer proxyPort,String userName,String pwd, boolean flag,String toolName){
		boolean connFlag =false;
		URL url = null;
		HttpURLConnection conn = null;
		StringBuffer  output = new StringBuffer();
		String line = null;
		if(toolName!= null && StringUtils.isNotEmpty(toolName)){
			
			if(toolName.equalsIgnoreCase(Constants.JIRA_TOOL) || toolName.equalsIgnoreCase(Constants.BITBUCKET_TOOL)){
				
				try {
					url = new URL(urlPath);
					Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIP, proxyPort));
					conn = (HttpURLConnection) url.openConnection(proxy);
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
				
			}else if(toolName.equalsIgnoreCase(Constants.JENKINS_TOOL) || toolName.equalsIgnoreCase(Constants.SONAR_TOOL)){
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
	public static List<DevopTool> fetchToolConfigDtsByShellScriptCall(List<Tools> formTools,String linuxHostName,String linuxUserName,String linuxPwd,Integer linuxPort,String shellScriptCmd,String fetchTextDtlsCmd)  {
		 List<DevopTool> ltDevToolPojo = new ArrayList<DevopTool>(); 
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
	  	  shellScriptCmd=shellScriptCmd.trim()+toolName;
	      logger.info("Trying to connect..and execute...shellScriptCmd:"+shellScriptCmd);
	      try{
		    	
		    	java.util.Properties config = new java.util.Properties(); 
		    	config.put("StrictHostKeyChecking", "no");
		    	session=jsch.getSession(linuxUserName, linuxHostName,linuxPort);
		    	session.setPassword(linuxPwd);
		    	session.setConfig(config);
		    	session.connect();
		    	logger.info("Connected");
		    	Channel channel=session.openChannel("exec");
		       ((ChannelExec)channel).setCommand(shellScriptCmd);
		        channel.setInputStream(null);
		        ((ChannelExec)channel).setErrStream(System.err);
		        channel.connect();
		        
		        while (channel.getExitStatus() == -1){
		            try{Thread.sleep(1000);}catch(Exception e){logger.error(e);break;}
		         }
		        channel.disconnect();

		        Channel channel1=session.openChannel("exec");
			       ((ChannelExec)channel1).setCommand(fetchTextDtlsCmd);
			        channel1.setInputStream(null);
			        ((ChannelExec)channel1).setErrStream(System.err);
		        
		        InputStream in=channel1.getInputStream();
		        channel1.connect();
		        byte[] tmp=new byte[1024];
		        
		        while(true){
		          while(in.available()>0){
		            int i=in.read(tmp, 0, 1024);
		            if(i<0){
		            	logger.info("Data is not there");
		            	break;
		            	
		            }else{
		            	logger.info("Data is there");
		            	System.out.print(new String(tmp, 0, i));
		           
		            Scanner scanner = new Scanner(new String(tmp, 0, i));
		            while (scanner.hasNextLine()) {
		              String line = scanner.nextLine();
		              logger.info("--"+line);
		              System.out.println(line+"==============================================");
		              DevopTool devopToolPojo = new DevopTool();
		              StringTokenizer st = new StringTokenizer(line,Constants.PIPE_SYMBOL);
			             while(st.hasMoreTokens()){
			            	 String string =st.nextToken();
			                 String[] parts = string.split(Constants.HASH_SYMBOL);
			                 String part2="";
			                 String part1 = parts[0].trim();
			                 if(parts.length>1 && null!=parts[1] && parts[1]!=""){
			                	 part2 = parts[1].trim();
			                 }
			                 if(part1!=null && part1.equalsIgnoreCase(Constants.TOOL_NAME)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setToolName(part2);
			                	 }
			                 }else if(part1!=null && part1.equalsIgnoreCase(Constants.URL)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setUrl(part2);
			                	 }
			                 }else if(part1!=null && part1.equalsIgnoreCase(Constants.USERNAME)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setUserName(part2);
			                	 }
			                 }else if(part1!=null && part1.equalsIgnoreCase(Constants.PASSWORD)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setPassword(part2);
			                	 }
			                 }else if(part1!=null && part1.equalsIgnoreCase(Constants.ACCESSKEY)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setAccessKey(part2);
			                	 }
			                 }else if(part1!=null && part1.equalsIgnoreCase(Constants.SECUREKEY)){
			                	 if(part2!=null && StringUtils.isNotEmpty(part2)){
			                		 devopToolPojo.setSecureKey(part2);
			                	 }
			                 }else{
			                	// logger.error("Fields are not Matching in the File");
			                 }
			                 
			             }
			             if(devopToolPojo!=null && devopToolPojo.getUrl()!=null){
			            	 	ltDevToolPojo.add(devopToolPojo);
				             }
		            }
		            scanner.close();
		            }  
		            logger.info("SIZE:"+ltDevToolPojo.size());
		          }
		          if(channel1.isClosed()){
		            System.out.println("exit-status: "+channel1.getExitStatus());
		            if(channel1.getExitStatus()==0){
		            	 System.out.println("exit-status: "+channel1.getExitStatus());
		            }
		            break;
		         }
		         }
		        channel1.disconnect();
		        session.disconnect();
		        logger.info("END");
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	      if(ltDevToolPojo!= null && ltDevToolPojo.size()>0){
		      for(DevopTool fb:ltDevToolPojo){
		    	  System.out.println("toolName:"+fb.getToolName()+"url:"+fb.getUrl()+"userName:"+fb.getUserName()+"pwd:"+fb.getPassword()+"acc:"+fb.getAccessKey()+"sec:"+fb.getSecureKey());
		      }
	      }
	      return  ltDevToolPojo;
	  }
	
}
