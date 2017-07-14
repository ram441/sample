package com.itcinfotech.zicos.sql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "inspect")
public class Inspect implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int inspectId;
	private Jobs jobId;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "inspect_id", unique = true, nullable = false)
	public int getInspectId() {
		return inspectId;
	}
	public void setInspectId(int inspectId) {
		this.inspectId = inspectId;
	}
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	public Jobs getJobId() {
		return jobId;
	}
	public void setJobId(Jobs jobId) {
		this.jobId = jobId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
