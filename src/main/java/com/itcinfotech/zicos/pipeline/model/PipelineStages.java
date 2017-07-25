package com.itcinfotech.zicos.pipeline.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pipeline_stages")
public class PipelineStages implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long stageId;
	private String stageName;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stage_id")
	public Long getStageId() {
		return stageId;
	}
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	
	@Column(name = "stage_name")
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
