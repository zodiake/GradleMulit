package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.AdvertisementCategory;
import com.sj.repository.model.AdvertisementCategoryJson;
import com.sj.repository.repository.AdvertisementCategoryRepostiory;
import com.sj.repository.service.AdvertisementCategoryService;

@Service
@Transactional
public class AdvertisementCategoryServiceImpl implements
		AdvertisementCategoryService {
	@Autowired
	private AdvertisementCategoryRepostiory repository;

	@Override
	public List<AdvertisementCategory> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public List<AdvertisementCategoryJson> findAllJson() {
		return findAll().stream().map(c -> new AdvertisementCategoryJson(c))
				.collect(Collectors.toList());
	}

}
