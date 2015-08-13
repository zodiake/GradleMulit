package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.CommonUser;

public interface CommonUserService {
	public CommonUser create(CommonUser user);
	
	public CommonUser findByName(String name);
	
	public Page<CommonUser> findAll(Pageable pageable);
	
	public CommonUser findOne(Long id);
}
