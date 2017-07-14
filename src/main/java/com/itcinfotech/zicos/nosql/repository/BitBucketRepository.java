package com.itcinfotech.zicos.nosql.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.itcinfotech.zicos.nosql.model.BitBucket;

public interface BitBucketRepository extends MongoRepository<BitBucket, String> {

	List<BitBucket> findBySha(String sha);

}
