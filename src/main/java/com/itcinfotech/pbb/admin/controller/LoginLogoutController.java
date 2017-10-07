package com.itcinfotech.pbb.admin.controller;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itcinfotech.pbb.admin.service.RoleService;
import com.itcinfotech.pbb.admin.service.UserService;
import com.itcinfotech.pbb.exceptions.EmailNotExistException;
import com.itcinfotech.pbb.exceptions.UserAlreadyExistException;
import com.itcinfotech.pbb.exceptions.UserNotFoundException;
import com.itcinfotech.pbb.sql.model.User;
import com.itcinfotech.pbb.utility.MailUtility;
import com.itcinfotech.pbb.sql.model.CustomUserDetails;



@RestController
public class LoginLogoutController {
	
	@Autowired
	MailUtility mailUtility;

	@Value("${pbb.forgot.pwd}")
	private String PBB_FORGOT_PWD;

	@Value("${mail.smtp.from}")
	private String mailFrom;

	static final Logger logger = LogManager.getLogger(LoginLogoutController.class.getName());
		@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;


	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Optional<User> loginUser() {

		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		return ((CustomUserDetails) user).getUser();
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgotPassword(@RequestParam("email") String userEmail, HttpServletRequest request) 
			throws EmailNotExistException, MalformedURLException, UserAlreadyExistException, UserNotFoundException {


		Optional<User> optional = userService.findUserByEmail(userEmail);

		if (!optional.isPresent()) {
			throw new EmailNotExistException();
		} else {

			// Generate random 36-character string token for reset password 
			User user = optional.get();
			user.setMailToken(UUID.randomUUID().toString());

			// Save token to database
			userService.updateUser(user);
			logger.info("mail token from database : "+user.getMailToken());			
			String appUrl = request.getScheme() + "://" + request.getServerName();
			logger.info("app URL for change new passsword"+appUrl);//  http://localhost
			// Email message
			URL aURL = new URL(request.getRequestURL().toString()); 
			logger.info(aURL.getProtocol()+"://"+aURL.getAuthority()+request.getContextPath()+PBB_FORGOT_PWD);
			Boolean statusmsg = mailUtility.sendPasswordResetMailToUser(user.getEmail(), aURL.getProtocol()+"://"+aURL.getAuthority()+request.getContextPath()+PBB_FORGOT_PWD+ "/?token=" + user.getMailToken());
			if(!statusmsg) {
				return "Email Sending failed.";
			}
			return "Mail Successfully sent.";
		}
	}
}

