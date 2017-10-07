package com.itcinfotech.pbb.sql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.pbb.sql.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByEmail(String email);
	//User findUserByUserName(String userName);
//	User findUserByEmail(String email);
	//Optional<User> findByResetToken(String resetToken);

}
