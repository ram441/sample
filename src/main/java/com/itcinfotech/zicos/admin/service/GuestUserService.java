package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.GuestUser;

public interface GuestUserService {

	public  List<GuestUser> loadAllGuestUsers();
	public  GuestUser findGuestUserByUserId(Long userId);
	public  GuestUser findGuestUserByUsernameOrMail(String mail);
	public GuestUser saveGuestUser(GuestUser guestUser);
	public GuestUser updateGuestUser(GuestUser guestUser);
	
}
