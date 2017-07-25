package com.itcinfotech.zicos.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.PermissionService;
import com.itcinfotech.zicos.admin.service.RoleService;
import com.itcinfotech.zicos.admin.service.UserService;
import com.itcinfotech.zicos.exceptions.EmailNotExistException;
import com.itcinfotech.zicos.exceptions.UserAlreadyExistException;
import com.itcinfotech.zicos.sql.model.CustomUserDetails;
import com.itcinfotech.zicos.sql.model.EndUser;
import com.itcinfotech.zicos.sql.model.Permission;
import com.itcinfotech.zicos.sql.model.Projects;
import com.itcinfotech.zicos.sql.model.Role;
import com.itcinfotech.zicos.sql.model.UserProjectConfig;
import com.itcinfotech.zicos.sql.repository.ProjectsRepository;
import com.itcinfotech.zicos.utils.MailUtility;

@RestController
public class LoginLogoutController {

	@Value("${zicos.forgot.pwd}")
	private String ZICOS_FORGOT_PWD;
	
	@Value("${taas.mail.from}")
	private String mailFrom;
	
	static final Logger logger = LogManager.getLogger(LoginLogoutController.class.getName());
	@Value("${user.expiredate}")
	private long guestUserExpireDays;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProjectsRepository projectsRepository;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	
	private MailUtility mailUtility;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public EndUser loginUser() {
		System.out.println(mailFrom+"+++++================================================");
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   
		if(user==null) {
	    	throw new UsernameNotFoundException("user not found");
	    }
	    return user.getEndUser();
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public EndUser signup(@RequestBody EndUser user) throws UserAlreadyExistException {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		if(null==user.getRole()) {
			Role role = roleService.getRole("GUEST");
			user.setRole(role);
			LocalDate exprirytDate = LocalDate.now().plusDays(guestUserExpireDays);
			user.setExpiryDate(Date.from(exprirytDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		    Projects project = projectsRepository.findByProjectName("DemoProject");
		    UserProjectConfig userProjectConfig = new UserProjectConfig();
		    Permission permission =  permissionService.getPermission("Read");
		    Set<Permission> permissions = new HashSet<>();
		    permissions.add(permission);
		    userProjectConfig.setPermissions(permissions);
		    userProjectConfig.setProject(project);
		    Set<UserProjectConfig> userProjectsConfigs = new HashSet<>();
		    userProjectsConfigs.add(userProjectConfig);
		    user.setUserProjectConfig(userProjectsConfigs);
		}
		user.setDisabled(false);
		user.setCreatedDate(new Date());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		return userService.saveUser(user);
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(@RequestParam("email") String email, HttpServletRequest request) throws EmailNotExistException, MalformedURLException {
	
		EndUser user = userService.findByEmailAndIsDisabled(email, false);
		if(null==user) {
			throw new EmailNotExistException();
		}
		logger.info("mail id is :: "+user.getEmail());
		URL aURL = new URL(request.getRequestURL().toString()); 
		logger.info(aURL.getProtocol()+"://"+aURL.getAuthority()+request.getContextPath()+ZICOS_FORGOT_PWD);
		Boolean statusmsg = mailUtility.sendPasswordResetMailToUser(user.getEmail(), aURL.getProtocol()+"://"+aURL.getAuthority()+request.getContextPath()+ZICOS_FORGOT_PWD);
        if(!statusmsg) {
        	return "Email Sending failed.";
        }
        return "Mail Successfully sent.";
	}
			
		


}