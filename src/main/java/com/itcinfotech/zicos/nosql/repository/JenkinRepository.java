package com.itcinfotech.zicos.nosql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itcinfotech.zicos.nosql.model.Jenkin;

public interface JenkinRepository extends MongoRepository<Jenkin, String> {

}