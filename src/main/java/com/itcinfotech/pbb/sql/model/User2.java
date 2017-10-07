package com.itcinfotech.pbb.sql.model;

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
@Table(name = "user")
public class User2 {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String createdBy;
	private Date createdDate;
	private Boolean isDelete;
	private Role role;
	private String password;
	private String resetToken;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Column(name = "email")
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "reset_token")
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
	
	
	

	/*private static final long serialVersionUID = 1L;

	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String createdBy;
	private Date createdDate;
	private Boolean isDelete;
	private Role role;
	private String password;
	private String resetToken;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Column(name = "email")
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
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "reset_token")
	public String getResetToken() {
		return resetToken;
	}
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}*/

}	