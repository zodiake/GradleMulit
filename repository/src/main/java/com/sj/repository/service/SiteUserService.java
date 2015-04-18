package com.sj.repository.service;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;

public interface SiteUserService {
	public SiteUser findByNameAndEnabled(String name,ActivateEnum activate);
	public SiteUser updatePassword(int id,String newPassword);
}
