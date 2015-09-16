package com.sj.admin.security;

import com.sj.model.model.SiteUser;

public interface UserContext {
	SiteUser getCurrnetUser();

	void setCurrentUser(SiteUser user);

	boolean isLogin();
}
