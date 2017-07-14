package com.itcinfotech.zicos.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itcinfotech.zicos.nosql.model.Jira;

public interface JiraRepository extends MongoRepository<Jira, String> {

}
