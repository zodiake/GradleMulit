package com.sj.repository.service.Impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteMenu;
import com.sj.model.model.SiteUser;
import com.sj.repository.model.SiteMenuJson;
import com.sj.repository.repository.SiteMenuRepository;
import com.sj.repository.service.SiteMenuService;
import com.sj.repository.service.SiteUserService;

@Service
@Transactional
public class SiteMenuServiceImpl implements SiteMenuService {
	@Autowired
	private SiteUserService userService;
	@Autowired
	private SiteMenuRepository repository;

	@Override
	public Set<SiteMenu> findByUser(SiteUser u) {
		SiteUser user = userService.findOne(u.getId());
		return user.getRoles().stream().flatMap(r -> r.getMenus().stream())
				.collect(Collectors.toSet());
	}

	@Override
	public List<SiteMenuJson> findAllJson() {
		return Lists.newArrayList(repository.findAll()).stream()
				.map(m -> new SiteMenuJson(m)).collect(Collectors.toList());
	}

}
