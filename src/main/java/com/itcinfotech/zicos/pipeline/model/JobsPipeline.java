package com.itcinfotech.zicos.pipeline.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.itcinfotech.zicos.sql.model.Projects;


@Entity
@Table(name = "jobs_pipeline")
public class JobsPipeline implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "job_id", unique = true, nullable = false)
	private Long jobId;
	
	@Column(name = "job_name")
	private String jobName;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name = "project_id")
	private Projects project;
	
	@Column(name = "is_assigned")
	private String isAssigned;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "env_id")
	private Environment env;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pipeline_def_id")
	private PipelineDef pipelineDef;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stage_id")
	private PipelineStages pipelineStages;
	
	@Column(name = "is_first_job")
	private boolean isFirstJob;

	
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	
	public Projects getProject() {
		return project;
	}
	public void setProject(Projects project) {
		this.project = project;
	}

	
	public String getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(String isAssigned) {
		this.isAssigned = isAssigned;
	}

	
	public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public PipelineDef getPipelineDef() {
		return pipelineDef;
	}
	public void setPipelineDef(PipelineDef pipelineDef) {
		this.pipelineDef = pipelineDef;
	}

	
	public PipelineStages getPipelineStages() {
		return pipelineStages;
	}
	public void setPipelineStages(PipelineStages pipelineStages) {
		this.pipelineStages = pipelineStages;
	}
	
	public boolean isFirstJob() {
		return isFirstJob;
	}
	public void setFirstJob(boolean isFirstJob) {
		this.isFirstJob = isFirstJob;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobsPipeline other = (JobsPipeline) obj;
		if (jobName == null) {
			if (other.jobName != null)
				return false;
		} else if (!jobName.equals(other.jobName))
			return false;
		return true;
	}
	public static JobsPipeline findPipeLineByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
