package com.sj.repository.service;

import java.util.List;
import java.util.Set;

import com.sj.model.model.SiteMenu;
import com.sj.model.model.SiteUser;
import com.sj.repository.model.SiteMenuJson;

public interface SiteMenuService {
	public Set<SiteMenu> findByUser(SiteUser u);

	public List<SiteMenuJson> findAllJson();
}
