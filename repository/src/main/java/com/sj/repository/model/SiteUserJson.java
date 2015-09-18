package com.sj.repository.model;

import com.sj.model.model.SiteUser;

public class SiteUserJson {
	private long id;
	private String name;

	public SiteUserJson() {
	}

	public SiteUserJson(SiteUser user) {
		this.id = user.getId();
		this.name = user.getName();
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
