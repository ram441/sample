package com.itcinfotech.zicos.admin.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.admin.service.GuestUserService;
import com.itcinfotech.zicos.sql.model.GuestUser;
import com.itcinfotech.zicos.sql.repository.GuestUserRepository;

@Service
public class GuestUserServiceImpl implements GuestUserService {

	private GuestUserRepository guestUserRepository;
	@Override
	public List<GuestUser> loadAllGuestUsers() {
		// TODO Auto-generated method stub
		return guestUserRepository.findAll();
	}

	@Override
	public GuestUser findGuestUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return guestUserRepository.findOne(userId);
	}

	@Override
	public GuestUser findGuestUserByUsernameOrMail(String mail) {
		// TODO Auto-generated method stub
		return guestUserRepository.findByEmail(mail);
	}

	@Override
	public GuestUser saveGuestUser(GuestUser guestUser) {
		// TODO Auto-generated method stub
		return guestUserRepository.save(guestUser);
	}

	@Override
	public GuestUser updateGuestUser(GuestUser guestUser) {
		// TODO Auto-generated method stub
		return guestUserRepository.save(guestUser);
	}
	
	
}
