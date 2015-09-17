package com.sj.repository.model;

import java.util.List;
import java.util.stream.Collectors;

import com.sj.model.model.SiteRole;

public class SiteRoleDetailJson extends SiteRoleJson {
	private List<SiteMenuJson> menus;

	public SiteRoleDetailJson(SiteRole role) {
		super(role);
		this.menus = role.getMenus().stream().map(r -> new SiteMenuJson(r))
				.collect(Collectors.toList());
	}

	public List<SiteMenuJson> getMenus() {
		return menus;
	}

	public void setMenus(List<SiteMenuJson> menus) {
		this.menus = menus;
	}

}
