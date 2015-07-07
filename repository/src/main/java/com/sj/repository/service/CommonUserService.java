package com.sj.repository.service;

import com.sj.model.model.CommonUser;

public interface CommonUserService {
	public CommonUser create(CommonUser user);
	
	public CommonUser findByName(String name);
}
