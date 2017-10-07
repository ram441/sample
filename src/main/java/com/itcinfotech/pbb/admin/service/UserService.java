package com.itcinfotech.pbb.admin.service;

import java.util.List;
import java.util.Optional;

import com.itcinfotech.pbb.sql.model.User;
import com.itcinfotech.pbb.exceptions.UserAlreadyExistException;
import com.itcinfotech.pbb.exceptions.UserNotFoundException;

public interface UserService {
	public  List<User> findAllUser();	
	public User saveUser(User user) throws UserAlreadyExistException;
	public User updateUser(User user) throws UserNotFoundException;
	public Optional<User> findUserByEmail(String email);
//	public User findUserByEmail(String email);
	//public Optional<User> findUserByResetToken(String resetToken);
	//public User findUserByUserName(String userName);	
}
