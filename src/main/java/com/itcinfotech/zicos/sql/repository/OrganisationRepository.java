package com.itcinfotech.zicos.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcinfotech.zicos.sql.model.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}
