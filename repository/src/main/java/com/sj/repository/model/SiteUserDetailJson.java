package com.sj.repository.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.sj.model.model.SiteUser;

public class SiteUserDetailJson extends SiteUserJson {
	private Set<SiteRoleJson> roles;

	public SiteUserDetailJson() {
		super();
	}

	public SiteUserDetailJson(SiteUser user) {
		super(user);
		this.roles = user.getRoles().stream().map(r -> new SiteRoleJson(r))
				.collect(Collectors.toSet());
	}

	public Set<SiteRoleJson> getRoles() {
		return roles;
	}

	public void setRoles(Set<SiteRoleJson> roles) {
		this.roles = roles;
	}
}
