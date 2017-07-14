package com.itcinfotech.zicos.nosql.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="jenkin")
public class Jenkin implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	public String jenkinId;
	public String buildId;
	public String couchId;
	public String dataType; 
	public String buildName;
	public String result;
	public String timestamp;
	public Integer buildDuration;
	public String lastBuiltRevision;
	public String jenkinsUrl;
	public String remoteUrl;
	public String causedBy;
	public Integer buildNumber;
	public String job;
	public Integer totalBuildCount;
	public Integer successBuildCount;
	public Integer failedBuildCount;
	public Integer abortedBuildCount;
	public Integer count;
	public Integer failureRate;
	public Map<String, Integer> map=null;
	public String shortDescription;
	public Integer lastBuildDuration;
	public Integer lastSuccessBuildDuration;
	public Integer lastUnSuccessBuildDuration;
	
	
	public String getJenkinId() {
		return jenkinId;
	}
	public void setJenkinId(String jenkinId) {
		this.jenkinId = jenkinId;
	}
	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getCouchId() {
		return couchId;
	}
	public void setCouchId(String couchId) {
		this.couchId = couchId;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getBuildDuration() {
		return buildDuration;
	}
	public void setBuildDuration(Integer buildDuration) {
		this.buildDuration = buildDuration;
	}
	public String getLastBuiltRevision() {
		return lastBuiltRevision;
	}
	public void setLastBuiltRevision(String lastBuiltRevision) {
		this.lastBuiltRevision = lastBuiltRevision;
	}
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}
	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}
	public String getRemoteUrl() {
		return remoteUrl;
	}
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	public String getCausedBy() {
		return causedBy;
	}
	public void setCausedBy(String causedBy) {
		this.causedBy = causedBy;
	}
	public Integer getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(Integer buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public Integer getTotalBuildCount() {
		return totalBuildCount;
	}
	public void setTotalBuildCount(Integer totalBuildCount) {
		this.totalBuildCount = totalBuildCount;
	}
	public Integer getSuccessBuildCount() {
		return successBuildCount;
	}
	public void setSuccessBuildCount(Integer successBuildCount) {
		this.successBuildCount = successBuildCount;
	}
	public Integer getFailedBuildCount() {
		return failedBuildCount;
	}
	public void setFailedBuildCount(Integer failedBuildCount) {
		this.failedBuildCount = failedBuildCount;
	}
	public Integer getAbortedBuildCount() {
		return abortedBuildCount;
	}
	public void setAbortedBuildCount(Integer abortedBuildCount) {
		this.abortedBuildCount = abortedBuildCount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getFailureRate() {
		return failureRate;
	}
	public void setFailureRate(Integer failureRate) {
		this.failureRate = failureRate;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Integer getLastSuccessBuildDuration() {
		return lastSuccessBuildDuration;
	}
	public void setLastSuccessBuildDuration(Integer lastSuccessBuildDuration) {
		this.lastSuccessBuildDuration = lastSuccessBuildDuration;
	}
	public Integer getLastBuildDuration() {
		return lastBuildDuration;
	}
	public void setLastBuildDuration(Integer lastBuildDuration) {
		this.lastBuildDuration = lastBuildDuration;
	}
	public Integer getLastUnSuccessBuildDuration() {
		return lastUnSuccessBuildDuration;
	}
	public void setLastUnSuccessBuildDuration(Integer lastUnSuccessBuildDuration) {
		this.lastUnSuccessBuildDuration = lastUnSuccessBuildDuration;
	}

	
	
}
