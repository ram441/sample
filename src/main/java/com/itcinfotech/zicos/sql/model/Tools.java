package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "tool")
public class Tools implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long toolId;
	private String toolName;
	private String toolDesc;
	
//	private List<DevopTool> devopTools = new ArrayList<DevopTool>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "tool_id", unique = true, nullable = false)
	public Long getToolId() {
		return toolId;
	}
	public void setToolId(Long toolId) {
		this.toolId = toolId;
	}
	@Column(name = "tool_name")
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	/**
	 * @return the toolDesc
	 */
	@Column(name = "tool_desc")
	public String getToolDesc() {
		return toolDesc;
	}
	/**
	 * @param toolDesc the toolDesc to set
	 */
	public void setToolDesc(String toolDesc) {
		this.toolDesc = toolDesc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((toolId == null) ? 0 : toolId.hashCode());
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
		Tools other = (Tools) obj;
		if (toolId == null) {
			if (other.toolId != null)
				return false;
		} else if (!toolId.equals(other.toolId))
			return false;
		return true;
	}
	
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "devopsTool")
	public List<DevopTool> getDevopTools() {
		return devopTools;
	}
	public void setDevopTools(List<DevopTool> devopTools) {
		this.devopTools = devopTools;
	}*/
	
}
