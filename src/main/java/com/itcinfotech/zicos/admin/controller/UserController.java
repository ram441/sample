package com.itcinfotech.zicos.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.admin.service.UserService;
import com.itcinfotech.zicos.exceptions.UserAlreadyExistException;
import com.itcinfotech.zicos.sql.model.EndUser;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{id}", method =RequestMethod.GET)
	public  EndUser findUserById(@PathVariable("id") Long id) {
		return userService.findUserById(id);
	}
	@RequestMapping(value="/user/{displayName}", method =RequestMethod.GET)
	public  EndUser getUserByDisplayName(@PathVariable("displayName") String displayName) {
		return userService.getUserByDisplayName(displayName);
	}
	@RequestMapping(value="/user", method =RequestMethod.GET)
	public  List<EndUser> findAllUsers() {
		return userService.findAllUsers();
	}
	@RequestMapping(value="/user", method =RequestMethod.POST)
	public EndUser saveUser(@RequestBody EndUser user) throws UserAlreadyExistException {
		return userService.saveUser(user);
	}
	@RequestMapping(value="/user", method =RequestMethod.PUT)
	public EndUser updateUser(@RequestBody EndUser user) {
		return userService.updateUser(user);
	}
}
