package com.sj.web.security;

import com.sj.model.model.SiteUser;

public interface UserContext {
	SiteUser getCurrentUser();

	void setCurrentUser(SiteUser user);

	boolean isLogin();
}
