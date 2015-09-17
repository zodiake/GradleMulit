package com.sj.repository.model;

import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.SiteRole;

public class SiteRoleJson {
	private long id;
	private String name;
	private List<SiteMenuJson> menus;

	public SiteRoleJson() {
	}

	public SiteRoleJson(SiteRole role) {
		this.id = role.getId();
		this.name = role.getRoleName();
		this.menus = role.getMenus().stream().map(r -> new SiteMenuJson(r))
				.collect(Collectors.toList());
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

	public List<SiteMenuJson> getMenus() {
		return menus;
	}

	public void setMenus(List<SiteMenuJson> menus) {
		this.menus = menus;
	}

}
