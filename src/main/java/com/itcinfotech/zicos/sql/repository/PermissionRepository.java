package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByName(String permissionName);

}
