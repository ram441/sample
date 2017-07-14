package com.itcinfotech.zicos.nosql.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcinfotech.zicos.nosql.model.BitBucket;
import com.itcinfotech.zicos.nosql.repository.BitBucketRepository;
import com.itcinfotech.zicos.nosql.service.BitBucketService;

@Service
public class BitBucketServiceImpl implements BitBucketService{

	@Autowired
	private BitBucketRepository bitBucketRepository;
	@Override
	public BitBucket saveBitBucketCommits(BitBucket bitBucket) {
		// TODO Auto-generated method stub
		return bitBucketRepository.save(bitBucket);
	}

	@Override
	public BitBucket updateBitBucketCommits(BitBucket bitBucket) {
		// TODO Auto-generated method stub
		return bitBucketRepository.save(bitBucket);
	}

	@Override
	public BitBucket getBitBucketBySha(String sha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BitBucket> getBitBucketsBySha(String sha) {
		// TODO Auto-generated method stub
		return bitBucketRepository.findBySha(sha);
	}
	
	
}
