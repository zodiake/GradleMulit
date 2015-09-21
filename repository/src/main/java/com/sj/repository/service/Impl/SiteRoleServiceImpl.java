package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.SiteRole;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SiteRoleDetailJson;
import com.sj.repository.model.SiteRoleJson;
import com.sj.repository.repository.SiteRoleRepository;
import com.sj.repository.service.SiteRoleService;

@Service
@Transactional
public class SiteRoleServiceImpl implements SiteRoleService {
	@Autowired
	private SiteRoleRepository repository;

	@Override
	public List<SiteRoleJson> findAllJson(ActivateEnum active) {
		List<SiteRole> lists;
		if (ActivateEnum.ACTIVATE.equals(active)) {
			lists = Lists.newArrayList(repository.findByActive(active));
		} else {
			lists = Lists.newArrayList(repository.findAll());
		}
		return lists.stream().map(r -> new SiteRoleJson(r))
				.collect(Collectors.toList());
	}

	@Override
	public SiteRoleDetailJson findOneJson(Long id) {
		return new SiteRoleDetailJson(repository.findOne(id));
	}

	@Override
	public SiteRole save(SiteRole role) {
		SiteRole siteRole = repository.findOne(role.getId());
		siteRole.setMenus(role.getMenus());
		siteRole.setRoleName(role.getRoleName());
		return siteRole;
	}

	@Override
	public SiteRole updateState(Long id, ActivateEnum active) {
		SiteRole role = repository.findOne(id);
		role.setActive(active);
		return role;
	}

}
