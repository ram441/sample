package com.itcinfotech.pbb.sql.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pbb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String createdBy;
	private Date createdDate;
	private Long roleId;
	private String password;
	private Long isDelete;
	private String mailToken;
	private Long userTypeId;
	private Long isSignatory;
	private Long isMailTokenActive;
	private Long isActive;
	private String location;
	private Long cityId;
	private Long countryId;
	private Long organizationId;
	private Long languageId;
	private Long loginFailureCount;
	// this domain bean is for login authorities
	//private Role role;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "email", unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "is_delete")
	public Long getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "mail_token")
	public String getMailToken() {
		return mailToken;
	}
	public void setMailToken(String mailToken) {
		this.mailToken = mailToken;
	}
	@Column(name = "user_type_id")
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Column(name = "is_signatory")
	public Long getIsSignatory() {
		return isSignatory;
	}
	public void setIsSignatory(Long isSignatory) {
		this.isSignatory = isSignatory;
	}
	@Column(name = "is_mail_token_active")
	public Long getIsMailTokenActive() {
		return isMailTokenActive;
	}
	public void setIsMailTokenActive(Long isMailTokenActive) {
		this.isMailTokenActive = isMailTokenActive;
	}
	@Column(name = "is_active")
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "role_id")
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	@Column(name = "country_id")
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	@Column(name = "organization_id")
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	@Column(name = "language_id")
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	@Column(name = "login_failure_count")
	public Long getLoginFailureCount() {
		return loginFailureCount;
	}
	public void setLoginFailureCount(Long loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	
	
}	