package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteUserDetailJson;
import com.sj.repository.model.SiteUserJson;

public interface SiteUserService {
	public SiteUser findByName(String name);

	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate);

	public SiteUser updatePassword(Long id, String newPassword);

	public Page<SiteUser> findAll(Pageable pageable);

	public void updateEnabledById(Long id, int state);

	public SiteUser findOne(Long id);

	public SiteUser findByPhone(String phone);

	public SiteUser findByEmail(String email);

	public SiteUser retrievePassword(String phone, String newPassword);

	public SiteUser findByNameAndSiteAuthority(SiteUser user);

	public Page<SiteUserJson> findBySiteAuthority(String authority,
			Pageable pageable);

	public SiteUser save(SiteUser user);

	public SiteUser update(SiteUser user);

	public SiteUserDetailJson findOneJson(Long id);
}
