package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name ="devop_tool")
public class DevopTool implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long devopToolId;
//	private DevopsTool  devopsTool;
	private String url;
	private String userName;
	private String password;
	private String creadtedBy;
	private String modifiedBy;
	private Date creadtedDate;
	private Date modifiedDate;
	
	private String accessKey;
	private String secureKey;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "devop_tool_id", unique = true, nullable = false)
	public Long getDevopToolId() {
		return devopToolId;
	}
	public void setDevopToolId(Long devopToolId) {
		this.devopToolId = devopToolId;
	}
//	@LazyCollection(LazyCollectionOption.FALSE)
	/*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "devops_tool_id")
	public DevopsTool getDevopsTool() {
		return devopsTool;
	}
	public void setDevopsTool(DevopsTool devopsTool) {
		this.devopsTool = devopsTool;
	}*/
	
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "creadted_by")
	public String getCreadtedBy() {
		return creadtedBy;
	}
	public void setCreadtedBy(String creadtedBy) {
		this.creadtedBy = creadtedBy;
	}
	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name = "creadted_date")
	public Date getCreadtedDate() {
		return creadtedDate;
	}
	public void setCreadtedDate(Date creadtedDate) {
		this.creadtedDate = creadtedDate;
	}
	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	/**
	 * @return the accessKey
	 */
	@Column(name = "access_key")
	public String getAccessKey() {
		return accessKey;
	}
	/**
	 * @param accessKey the accessKey to set
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	/**
	 * @return the secureKey
	 */
	@Column(name = "secure_key")
	public String getSecureKey() {
		return secureKey;
	}
	/**
	 * @param secureKey the secureKey to set
	 */
	public void setSecureKey(String secureKey) {
		this.secureKey = secureKey;
	}
	/**
	 * @return the url
	 */
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	

}
