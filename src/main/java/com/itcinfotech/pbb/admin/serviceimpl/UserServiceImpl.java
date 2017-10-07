package com.itcinfotech.pbb.admin.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itcinfotech.pbb.admin.service.UserService;
import com.itcinfotech.pbb.exceptions.UserAlreadyExistException;
import com.itcinfotech.pbb.exceptions.UserNotFoundException;
import com.itcinfotech.pbb.sql.model.User;
import com.itcinfotech.pbb.sql.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public User saveUser(User user) throws UserAlreadyExistException {
		Optional<User> userDomain = userRepository.findUserByEmail(user.getEmail());
		logger.info("isExist : "+userDomain);
        if(userDomain.isPresent()) {
        	throw new UserAlreadyExistException();
        }
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) throws UserNotFoundException  {
		// TODO Auto-generated method stub
		User existedUser = userRepository.getOne(user.getUserId());
		 if(existedUser.getUserId() != null) {
	        	throw new UserNotFoundException();
	        }
		if(user.getUserId() != null)
		existedUser.setUserId(user.getUserId());
		if(user.getCityId() != null)
		existedUser.setCityId(user.getCityId());
		if(user.getCountryId() != null)
		existedUser.setCountryId(user.getCountryId());
		if(user.getCreatedBy() != null)
		existedUser.setCreatedBy(user.getCreatedBy());
		if(user.getCreatedDate() != null)
		existedUser.setCreatedDate(user.getCreatedDate());
		if(user.getEmail() != null)
		existedUser.setEmail(user.getEmail());
		if(user.getFirstName() != null)
		existedUser.setFirstName(user.getFirstName());
		if(user.getIsActive() != null)
		existedUser.setIsActive(user.getIsActive());
		if(user.getIsDelete() != null)
		existedUser.setIsDelete(user.getIsDelete());
		if(user.getIsMailTokenActive() != null)
		existedUser.setIsMailTokenActive(user.getIsMailTokenActive());
		if(user.getIsSignatory() != null)
		existedUser.setIsSignatory(user.getIsSignatory());
		if(user.getLanguageId() != null)
		existedUser.setLanguageId(user.getLanguageId());
		if(user.getLastName() != null)
		existedUser.setLastName(user.getLastName());
		if(user.getLocation() != null)
		existedUser.setLocation(user.getLocation());
		if(user.getLoginFailureCount() != null)
		existedUser.setLoginFailureCount(user.getLoginFailureCount());
		if(user.getMailToken() != null)
		existedUser.setMailToken(user.getMailToken());
		if(user.getOrganizationId() != null)
		existedUser.setOrganizationId(user.getOrganizationId());
		if(user.getPassword() != null)
		existedUser.setPassword(user.getPassword());
		if(user.getPhone() != null)
		existedUser.setPhone(user.getPhone());
		if(user.getRoleId() != null)
		existedUser.setRoleId(user.getRoleId());
		if(user.getUserTypeId() != null)
		existedUser.setUserTypeId(user.getUserTypeId());
		
		return userRepository.save(existedUser);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUserByEmail(email);
	}

	
	
}
