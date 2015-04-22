package com.sj.web.security;

import com.sj.model.model.SiteUser;

public interface UserContext {
	SiteUser getCurrnetUser();

	void setCurrentUser(SiteUser user);
	
	boolean isLogin();
}
