package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.UserService;
import com.itcinfotech.zicos.exceptions.UserAlreadyExistException;
import com.itcinfotech.zicos.sql.model.EndUser;
import com.itcinfotech.zicos.sql.repository.EndUserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EndUserRepository endUserRepository;
	@Override
	public EndUser findUserById(Long id) {
		// TODO Auto-generated method stub
		return endUserRepository.getOne(id);
	}

	@Override
	public EndUser getUserByDisplayName(String displayName) {
		// TODO Auto-generated method stub
		return endUserRepository.findByDisplayName(displayName);
	}

	@Override
	public List<EndUser> findAllUsers() {
		// TODO Auto-generated method stub
		return endUserRepository.findAll();
	}

	@Override
	public EndUser saveUser(EndUser user) throws UserAlreadyExistException {
		EndUser isExist = endUserRepository.findByEmail(user.getEmail());
        if(isExist!=null) {
        	throw new UserAlreadyExistException();
        }
		return endUserRepository.save(user);
	}

	@Override
	public EndUser updateUser(EndUser user) {
		// TODO Auto-generated method stub
		return endUserRepository.save(user);
	}

	@Override
	public EndUser findByEmail(String username) {
		// TODO Auto-generated method stub
		return endUserRepository.findByEmail(username);
	}

	@Override
	public EndUser findByEmailAndIsDisabled(String username, boolean b) {
		// TODO Auto-generated method stub
		return endUserRepository.findByEmailAndDisabled(username,b);
	}
	
	
}
