package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	@Transactional(readOnly = true)
	public Page<SiteUser> findAll(Pageable pageable) {
		Page<SiteUser> users = repository.findAll(pageable);
		return users;
	}

	@Override
	public void updateEnabledById(int id, int state) {
		ActivateEnum stateEnum = ActivateEnum.values()[state];
		repository.updateEnabled(stateEnum, id);
	}

	@Override
	public SiteUser findOne(int id) {
		return repository.findOne(id);
	}

}
