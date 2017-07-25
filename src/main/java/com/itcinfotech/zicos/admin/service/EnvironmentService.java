package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.pipeline.model.Environment;


public interface EnvironmentService {

	public  List<Environment> loadAllEnvironments(Long projId);
}
