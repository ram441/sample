package com.itcinfotech.pbb.admin.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.pbb.admin.service.UserService;
import com.itcinfotech.pbb.sql.model.User;
import com.itcinfotech.pbb.exceptions.UserAlreadyExistException;
import com.itcinfotech.pbb.exceptions.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	static final Logger logger = LogManager.getLogger(UserController.class.getName());
	
	@RequestMapping(value="/user", method =RequestMethod.GET)
	public  List<User> findAllUser() {
		return userService.findAllUser();
	}
	
	@RequestMapping(value="/registerUser", method =RequestMethod.POST)
	public User registerUser(@RequestBody User user) throws UserAlreadyExistException {
		logger.info("Creating the User... "+user);
		return userService.saveUser(user);
	}
	@RequestMapping(value="/updateuser", method =RequestMethod.PUT)
	public User updateUser(@RequestBody User user) throws UserNotFoundException {
		return userService.updateUser(user);
	}
}
