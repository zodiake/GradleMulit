package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.SiteUserRepository;
import com.sj.repository.service.SiteUserService;

@Service
public class SiteUserServiceImpl implements SiteUserService {
	@Autowired
	private SiteUserRepository repository;

	@Override
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate) {
		return repository.findByNameAndEnabled(name, activate);
	}

}
