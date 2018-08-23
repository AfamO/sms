package com.longbridge.sams.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.longbridge.sams.model.UserType;

import java.util.Collection;


public class UserDetailsImpl implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 928791098309131560L;
	private Collection<SimpleGrantedAuthority> authorities;
    private String username;
    private String password;
    private Boolean enabled = false;
    private Boolean nonLocked = false;
    private Boolean credentialsNotExpired = false;
    private Boolean notExpired = false;
    
    
    //secondary properties
    
    private String role;
    
    private UserType type;
    
    private Long sid;

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getNonLocked() {
		return nonLocked;
	}

	public void setNonLocked(Boolean nonLocked) {
		this.nonLocked = nonLocked;
	}

	public Boolean getCredentialsNotExpired() {
		return credentialsNotExpired;
	}

	public void setCredentialsNotExpired(Boolean credentialsNotExpired) {
		this.credentialsNotExpired = credentialsNotExpired;
	}

	public Boolean getNotExpired() {
		return notExpired;
	}

	public void setNotExpired(Boolean notExpired) {
		this.notExpired = notExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

	@Override
	public boolean isAccountNonExpired() {
		return notExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNotExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	
}
