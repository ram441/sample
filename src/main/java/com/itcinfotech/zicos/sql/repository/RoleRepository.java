package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String roleName);

}
