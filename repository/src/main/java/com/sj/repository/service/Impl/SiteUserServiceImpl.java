package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.repository.SiteUserRepository;
import com.sj.repository.service.SiteUserService;

@Service
@Transactional
public class SiteUserServiceImpl implements SiteUserService {
	@Autowired
	private SiteUserRepository repository;

	@Override
	@Transactional(readOnly = true)
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate) {
		return repository.findByNameAndEnabled(name, activate);
	}

	@Override
	public SiteUser updatePassword(int id, String newPassword) {
		SiteUser u = repository.findOne(id);
		u.setPassword(newPassword);
		repository.save(u);
		return u;
	}

}
