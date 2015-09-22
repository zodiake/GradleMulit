package com.sj.repository.service.Impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.SiteUser;
import com.sj.model.type.ActivateEnum;
import com.sj.model.type.SexEnum;
import com.sj.repository.model.SiteUserDetailJson;
import com.sj.repository.model.SiteUserJson;
import com.sj.repository.repository.SiteUserRepository;
import com.sj.repository.service.SiteUserService;

@Service
@Transactional
public class SiteUserServiceImpl implements SiteUserService {
	@Autowired
	private SiteUserRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public SiteUser findByNameAndEnabled(String name, ActivateEnum activate) {
		return repository.findByNameAndEnabled(name, activate);
	}

	@Override
	public SiteUser updatePassword(Long id, String newPassword) {
		SiteUser u = repository.findOne(id);
		u.setPassword(newPassword);
		return u;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SiteUser> findAll(Pageable pageable) {
		Page<SiteUser> users = repository.findAll(pageable);
		return users;
	}

	@Override
	public void updateEnabledById(Long id, int state) {
		ActivateEnum stateEnum = ActivateEnum.values()[state];
		Calendar calendar = Calendar.getInstance();
		repository.updateEnabled(stateEnum, calendar, id);
	}

	@Override
	public SiteUser findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public SiteUser findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public SiteUser findByPhone(String phone) {
		return repository.findByPhone(phone);
	}

	@Override
	public SiteUser retrievePassword(String phone, String newPassword) {
		SiteUser user = repository.findByPhone(phone);
		user.setPassword(newPassword);
		return repository.save(user);
	}

	@Override
	public SiteUser findByNameAndSiteAuthority(SiteUser user) {

		return repository.findByNameAndEnabledAndSiteAuthority(user.getName(),
				user.getEnabled(), user.getSiteAuthority());
	}

	@Override
	public SiteUser findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Page<SiteUserJson> findBySiteAuthority(String authority,
			Pageable pageable) {
		Page<SiteUser> pages = repository.findBySiteAuthority(authority,
				pageable);
		List<SiteUserJson> results = pages.getContent().stream()
				.map(p -> new SiteUserJson(p)).collect(Collectors.toList());
		return new PageImpl<SiteUserJson>(results, pageable,
				pages.getTotalElements());
	}

	@Override
	public SiteUser save(SiteUser user) {
		user.setSiteAuthority("ROLE_ADMIN");
		user.setEnabled(ActivateEnum.ACTIVATE);
		user.setCreateTime(Calendar.getInstance());
		user.setSex(SexEnum.MALE);
		user.setEmail("admin@admin.com");
		user.setRealName("admin");
		SiteUser u = repository.save(user);
		return u;
	}

	@Override
	public SiteUser update(SiteUser user) {
		SiteUser u = repository.findOne(user.getId());
		u.setPassword(user.getPassword());
		u.setRoles(user.getRoles());
		return u;
	}

	@Override
	public SiteUserDetailJson findOneJson(Long id) {
		SiteUser user = findOne(id);
		return new SiteUserDetailJson(user);
	}

}
