package com.sj.repository.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;

public interface SiteUserService {
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate);

	public SiteUser updatePassword(int id, String newPassword);

	public Page<SiteUser> findAll(Pageable pageable);

	public void updateEnabledById(int id,int state);
	
	public SiteUser findOne(int id);
}
