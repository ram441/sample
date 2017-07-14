package com.itcinfotech.zicos.nosql.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="bitbucket")
public class BitBucket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String _class;//mogo reference class
	String key; 
	String sha;	 
	Date created;	 
	String author;	 
	String repo;
	String scmAction; 
	Integer linesAdded;
	Integer linesRemoved; 
	Integer commentCount;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	public String getScmAction() {
		return scmAction;
	}
	public void setScmAction(String scmAction) {
		this.scmAction = scmAction;
	}
	public Integer getLinesAdded() {
		return linesAdded;
	}
	public void setLinesAdded(Integer linesAdded) {
		this.linesAdded = linesAdded;
	}
	public Integer getLinesRemoved() {
		return linesRemoved;
	}
	public void setLinesRemoved(Integer linesRemoved) {
		this.linesRemoved = linesRemoved;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}

}
