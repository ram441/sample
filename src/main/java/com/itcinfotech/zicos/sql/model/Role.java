package com.itcinfotech.zicos.sql.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "role")
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer roleId;
	
	private String name;
	
	
//	private List<Permission> Permissions; //for spring security
//	private List<EndUser> endUsers = new ArrayList<EndUser>(0);
	private Set<DashBoardComponents> dashBoardComponents = new HashSet<DashBoardComponents>(0);
//	private Set<Permission> rolePermissions = new HashSet<Permission>(0);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//for spring security
	/*@JsonIgnore
	@Transient
	public List<Permission> getPermissions() {
		return Permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		Permissions = permissions;
	}*/
	
	/*@Transient
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}*/

	/*@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public List<EndUser> getEndUsers() {
		return endUsers;
	}
	public void setEndUsers(List<EndUser> endUsers) {
		this.endUsers = endUsers;
	}*/
	//@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "dashboard_comp_role_xref", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns={@JoinColumn(name = "dashboard_comp_id")})
	public Set<DashBoardComponents> getDashBoardComponents() {
		return dashBoardComponents;
	}
	public void setDashBoardComponents(Set<DashBoardComponents> dashBoardComponents) {
		this.dashBoardComponents = dashBoardComponents;
	}
	
}
