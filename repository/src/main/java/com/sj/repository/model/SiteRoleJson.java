package com.sj.repository.model;

import com.sj.model.model.SiteRole;

public class SiteRoleJson {
	private long id;
	private String name;

	public SiteRoleJson() {
	}

	public SiteRoleJson(SiteRole role) {
		this.id = role.getId();
		this.name = role.getRoleName();
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

}
