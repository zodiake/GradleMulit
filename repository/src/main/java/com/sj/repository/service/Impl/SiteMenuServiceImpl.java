package com.sj.repository.service.Impl;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteMenu;
import com.sj.model.model.SiteUser;
import com.sj.repository.service.SiteMenuService;
import com.sj.repository.service.SiteUserService;

@Service
@Transactional
public class SiteMenuServiceImpl implements SiteMenuService {
	@Autowired
	private SiteUserService userService;

	@Override
	public Set<SiteMenu> findByUser(SiteUser u) {
		SiteUser user = userService.findOne(u.getId());
		return user.getRoles().stream().flatMap(r -> r.getMenus().stream())
				.collect(Collectors.toSet());
	}
}
