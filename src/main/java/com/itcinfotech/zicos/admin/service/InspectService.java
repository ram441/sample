package com.itcinfotech.zicos.admin.service;

import java.util.List;

import com.itcinfotech.zicos.sql.model.Inspect;

public interface InspectService  {
	public  List<Inspect> loadAllInspect();
	public Inspect findInspectByJobId(Long jobId);
	public Inspect findInspectByJobName(String jobName);
	public Inspect saveInspect(Inspect inspect);
	public Inspect updateInspect(Inspect inspect);
}
