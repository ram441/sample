package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.EndUser;

public interface EndUserRepository extends JpaRepository<EndUser, Long> {

	EndUser findByDisplayName(String displayName);

	EndUser findByEmail(String username);

	EndUser findByEmailAndDisabled(String username, boolean b);

}
