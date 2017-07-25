package com.itcinfotech.zicos.sql.model;




public class Build implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String jobName;
	public String buildId;
	public Integer buildNumber;
	public String timestamp;
	public String duration;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public Integer getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(Integer buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
