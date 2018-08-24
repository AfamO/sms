package com.longbridge.sams.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import javax.persistence.Column;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "[user]")
public class User extends AbstractSchoolEntity{

	/**
	 * 
	 */
	public static final String USER = "USER_TOKEN";
	private static final String REGULAR_USER = "ROLE_USER";
	private static final String INVALID_PWD = "ROLE_PWD_RESET";
	private String password;
	private UserType type;
	private UserStatus status;
	private Date expiryDate;
	private Date lastLoginDate;
	@Column(unique=true) @NotEmpty
	private String loginId;
	private Date passwordExpiryDate;
	
	
	
	public User(){
	}
	
	public Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}
	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}

	@ManyToOne @NotNull
	private Role role;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
}
