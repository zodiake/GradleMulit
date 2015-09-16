package com.sj.web.security;

import org.springframework.security.core.GrantedAuthority;

import com.sj.model.model.SiteUser;

public interface UserContext {
	SiteUser getCurrentUser();

	void setCurrentUser(SiteUser user);

	boolean isLogin();
	
	boolean hasRole(GrantedAuthority authority);
}
