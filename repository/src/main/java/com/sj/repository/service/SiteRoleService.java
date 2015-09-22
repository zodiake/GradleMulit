package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.SiteRole;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteRoleDetailJson;
import com.sj.repository.model.SiteRoleJson;

public interface SiteRoleService {
	public List<SiteRoleJson> findAllJson(ActivateEnum active);

	public SiteRoleDetailJson findOneJson(Long id);

	public SiteRole save(SiteRole role);

	public SiteRole update(SiteRole role);

	public SiteRole updateState(Long id, ActivateEnum active);
}
