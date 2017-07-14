package com.itcinfotech.zicos.nosql.model;

import java.util.Date;
import java.util.List;

public class Jira {

	public String couchId;
    public String key;
    public Date createdDate;
    public String createdBy;
    public String issuetype;
    public int movedForward;
    public int movedBackward;
    public int storyPoints;
//    public String[] assignees; //this is actually a map of stuff
    public String assignees;
    public List<String> tags;
    public String dataType;
    public Date finished;
    public long leadTime;
    public long devTime;
    public int commentCount;
    public String jiraProject;
    public int estimateHealth;
    public long rawEstimateHealth;
    public List<String> components;
    public String product;
    public String description;
    
	public String getCouchId() {
		return couchId;
	}
	public void setCouchId(String couchId) {
		this.couchId = couchId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreated(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	public int getMovedForward() {
		return movedForward;
	}
	public void setMovedForward(int movedForward) {
		this.movedForward = movedForward;
	}
	public int getMovedBackward() {
		return movedBackward;
	}
	public void setMovedBackward(int movedBackward) {
		this.movedBackward = movedBackward;
	}
	public int getStoryPoints() {
		return storyPoints;
	}
	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}
	public String getAssignees() {
		return assignees;
	}
	public void setAssignees(String assignees) {
		this.assignees = assignees;
	}
	
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Date getFinished() {
		return finished;
	}
	public void setFinished(Date finished) {
		this.finished = finished;
	}
	public long getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(long leadTime) {
		this.leadTime = leadTime;
	}
	public long getDevTime() {
		return devTime;
	}
	public void setDevTime(long devTime) {
		this.devTime = devTime;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getJiraProject() {
		return jiraProject;
	}
	public void setJiraProject(String jiraProject) {
		this.jiraProject = jiraProject;
	}
	public int getEstimateHealth() {
		return estimateHealth;
	}
	public void setEstimateHealth(int estimateHealth) {
		this.estimateHealth = estimateHealth;
	}
	public long getRawEstimateHealth() {
		return rawEstimateHealth;
	}
	public void setRawEstimateHealth(long rawEstimateHealth) {
		this.rawEstimateHealth = rawEstimateHealth;
	}
	
	public String getProduct() {
		return product;
	}
	public List<String> getComponents() {
		return components;
	}
	public void setComponents(List<String> components) {
		this.components = components;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
    
}
