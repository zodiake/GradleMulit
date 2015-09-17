package com.sj.repository.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.sj.model.model.SiteUser;

public class SiteUserJson {
	private long id;
	private String name;
	private Set<SiteRoleJson> roles;

	public SiteUserJson() {
	}

	public SiteUserJson(SiteUser user) {
		this.id = user.getId();
		this.name = user.getName();
		this.roles = user.getRoles().stream().map(r -> new SiteRoleJson(r))
				.collect(Collectors.toSet());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SiteRoleJson> getRoles() {
		return roles;
	}

	public void setRoles(Set<SiteRoleJson> roles) {
		this.roles = roles;
	}
}
