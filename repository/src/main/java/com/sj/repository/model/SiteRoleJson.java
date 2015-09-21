package com.sj.repository.model;

import com.sj.model.model.SiteRole;
import com.sj.model.type.ActivateEnum;

public class SiteRoleJson {
	private long id;
	private String name;
	private ActivateEnum state;

	public SiteRoleJson() {
	}

	public SiteRoleJson(SiteRole role) {
		this.id = role.getId();
		this.name = role.getRoleName();
		this.state = role.getActive();
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

	public ActivateEnum getState() {
		return state;
	}

	public void setState(ActivateEnum state) {
		this.state = state;
	}
}
