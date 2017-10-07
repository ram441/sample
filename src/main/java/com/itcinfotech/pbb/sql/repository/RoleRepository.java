package com.itcinfotech.pbb.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.pbb.sql.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
