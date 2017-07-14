package com.itcinfotech.zicos.sql.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class EndUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long endUserId;
	private String displayName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String contactNumber;
	private String email;
	private String password;
	private String creadtedBy;
	private String modifiedBy;
	private Date creadtedDate;
	private Date modifiedDate;
	private Role role;
	private String imagePath;
	private String designation;
	private Boolean status;
	private String contactMobile;
	
	
	private Set<UserProjectConfig> userProjectConfig;
//	private 
	
//	private Set<Projects> projects = new HashSet<Projects>(0);
	public EndUser() {
		
	}
	private EndUser(UserBuilder userBuilder) {
		 this.displayName = userBuilder.displayName;
		 this.firstName = userBuilder.firstName;
		 this.middleName = userBuilder.middleName;
		 this.lastName= userBuilder.lastName;
		 this.contactNumber = userBuilder.contactNumber;
		 this.email = userBuilder.email;
		 this.password = userBuilder.password;
		 this.creadtedBy = userBuilder.creadtedBy;
		 this.modifiedBy = userBuilder.modifiedBy;
		 this.creadtedDate = userBuilder.creadtedDate;
		 this.modifiedDate = userBuilder.modifiedDate;
		 this.role = userBuilder.role;
		 this.imagePath = userBuilder.imagePath;
		 this.designation = userBuilder.designation;
		 this.status =  userBuilder.status;
		 this.contactMobile = userBuilder.contactMobile ;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getEndUserId() {
		return endUserId;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	public Set<UserProjectConfig> getUserProjectConfig() {
		return userProjectConfig;
	}
	public void setUserProjectConfig(Set<UserProjectConfig> userProjectConfig) {
		this.userProjectConfig = userProjectConfig;
	}
	public void setEndUserId(Long endUserId) {
		this.endUserId = endUserId;
	}
	
	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "middle_name")
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email",nullable=false,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
//	@LazyCollection(LazyCollectionOption.FALSE)
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@JsonIgnore
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "contact_number")
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	/*@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name = "user_project_xref", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns={@JoinColumn(name = "project_id")})
	public Set<Projects> getProjects() {
		return projects;
	}

	public void setProjects(Set<Projects> projects) {
		this.projects = projects;
	}*/
	@Column(name = "image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name = "status")
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Column(name ="contact_mobile")
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/*@ManyToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable(name="end_user_role", 
                joinColumns={@JoinColumn(name="end_user_id")}, 
                inverseJoinColumns={@JoinColumn(name="role_id")})*/
	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/

	public static class UserBuilder {
		
		private String displayName;
		private String firstName;
		private String middleName;
		private String lastName;
		private String contactNumber;
		private String email;
		private String password;
		private String creadtedBy;
		private String modifiedBy;
		private Date creadtedDate;
		private Date modifiedDate;
		private Role role;
		private String imagePath;
		private String designation;
		private Boolean status;
		private String contactMobile;
		
		public UserBuilder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}
		public UserBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		public UserBuilder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}
		public UserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public UserBuilder contactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
			return this;
		}
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		public UserBuilder creadtedBy(String creadtedBy) {
			this.creadtedBy = creadtedBy;
			return this;
		}
		public UserBuilder modifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
			return this;
		}
		public UserBuilder creadtedDate(Date creadtedDate) {
			this.creadtedDate = creadtedDate;
			return this;
		}
		public UserBuilder modifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
			return this;
		}
		public UserBuilder role(Role role) {
			this.role = role;
			return this;
		}
		public UserBuilder imagePath(String imagePath) {
			this.imagePath = imagePath;
			return this;
		}
		public UserBuilder designation(String designation) {
			this.designation = designation;
			return this;
		}
		public UserBuilder status(boolean status) {
			this.status = status;
			return this;
		}
		public UserBuilder contactMobile(String contactMobile) {
			this.contactMobile = contactMobile;
			return this;
		}
		
		public EndUser build() {
			return new EndUser(this);
		}
	}
}
