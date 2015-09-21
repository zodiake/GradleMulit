package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SiteMenu;
import com.sj.repository.model.SiteMenuJson;
import com.sj.repository.repository.MenuRepository;
import com.sj.repository.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository repository;

	@Override
	@Transactional
	public List<SiteMenuJson> findAll() {
		List<SiteMenu> menus = Lists.newArrayList(repository.findAll());
		return menus.stream().map(m -> new SiteMenuJson(m))
				.collect(Collectors.toList());
	}
}
