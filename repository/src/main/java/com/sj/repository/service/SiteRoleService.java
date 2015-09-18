package com.sj.repository.service;

import java.util.List;

import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteRoleJson;

public interface SiteRoleService {
	public List<SiteRoleJson> findAllJson(ActivateEnum active);
}
