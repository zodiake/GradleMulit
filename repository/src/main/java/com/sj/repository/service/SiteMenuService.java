package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.SiteMenu;
import com.sj.model.model.SiteUser;

public interface SiteMenuService {
	public Set<SiteMenu> findByUser(SiteUser u);
}
