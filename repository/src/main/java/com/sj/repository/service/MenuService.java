package com.sj.repository.service;

import java.util.List;

import com.sj.repository.model.SiteMenuJson;

public interface MenuService {
	public List<SiteMenuJson> findAll();
}
