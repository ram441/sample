package com.itcinfotech.zicos.nosql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itcinfotech.zicos.nosql.model.BitBucket;
import com.itcinfotech.zicos.nosql.service.BitBucketService;

@RestController
public class BitBucketController {

	@Autowired
	private BitBucketService bitBucketService;
	
	@RequestMapping(value="/bitbucket", method =RequestMethod.POST)
	public BitBucket saveBitBucketCommits(@RequestBody BitBucket bitBucket) {
		return bitBucketService.saveBitBucketCommits(bitBucket);
	}

	@RequestMapping(value="/bitbucket", method =RequestMethod.PUT)
	public BitBucket updateBitBucketCommits(@RequestBody BitBucket bitBucket) {
		return bitBucketService.updateBitBucketCommits(bitBucket);
	}

	@RequestMapping(value="/bitbucket",params={"sha"}, method =RequestMethod.GET)
	public BitBucket getBitBucketBySha(@RequestParam("sha") String sha) {
		return bitBucketService.getBitBucketBySha(sha);
	}

	public List<BitBucket> getBitBucketsBySha(String sha) {
		return bitBucketService.getBitBucketsBySha(sha);
	}
}
