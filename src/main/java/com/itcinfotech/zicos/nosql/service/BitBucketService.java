package com.itcinfotech.zicos.nosql.service;

import java.util.List;

import com.itcinfotech.zicos.nosql.model.BitBucket;

public interface BitBucketService {
	public BitBucket saveBitBucketCommits(BitBucket bitBucket);

	public BitBucket updateBitBucketCommits(BitBucket bitBucket);

	public BitBucket getBitBucketBySha(String sha);

	public List<BitBucket> getBitBucketsBySha(String sha);
}
