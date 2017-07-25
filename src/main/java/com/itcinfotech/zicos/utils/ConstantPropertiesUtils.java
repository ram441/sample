package com.itcinfotech.zicos.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils {

	@Value("${jenkin.url}")
	public String jenkinUrl;
	
	@Value("${jenkin.username}")
	public String jenkinsusername;

	@Value("${jenkin.password}")
	public String jenkinspassword;

	@Value("${jenkin.port}")
	public Integer jenkinPort;

	@Value("${jenkin.host}")
	public String jenkinHost;
	
	@Value("${proxy.ip_address}")
	public String proxyIpAddress;
	
	@Value("${proxy.scheme}")
	public String proxyScheme;

	@Value("${target.scheme}")
	public String targetScheme;
	
	@Value("${proxy.port}")
	public Integer proxyPort;

	@Value("${proxy.enable}")
	public boolean proxyEnable;



}
