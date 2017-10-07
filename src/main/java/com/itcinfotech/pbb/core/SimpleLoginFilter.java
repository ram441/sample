package com.itcinfotech.pbb.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

public class SimpleLoginFilter implements Filter {
	
	public SimpleLoginFilter() {
	}
 
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;
			/*if(request.getHeader(HttpHeaders.REFERER)!=null){
				String requestBaseURL = request.getHeader(HttpHeaders.REFERER).substring(0, (StringUtils.ordinalIndexOf(request.getHeader(HttpHeaders.REFERER), "/", 3)));
				response.setHeader("Access-Control-Allow-Origin", requestBaseURL);
			}else*/
				response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods",
					"POST, GET, OPTIONS, DELETE, PUT");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers",
					"x-requested-with, authorization, Content-Type, Origin, Accept, x-auth-token, InvalidHeader");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Expose-Headers", "x-auth-token");
			if(response.getStatus()==401) {
			  // 	response.setStatus(200);
			   	response.setHeader("Access-Control-Expose-Headers", "InvalidHeader");
					response.setHeader("InvalidHeader", "Invalid User");
			}else {
				chain.doFilter(request, response);
			}
			//chain.doFilter(request, response);
	}   
			
		
	
	 

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}
