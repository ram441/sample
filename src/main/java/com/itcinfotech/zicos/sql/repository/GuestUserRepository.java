package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.GuestUser;

public interface GuestUserRepository extends JpaRepository<GuestUser, Long> {

	GuestUser findByEmail(String mail);

}
