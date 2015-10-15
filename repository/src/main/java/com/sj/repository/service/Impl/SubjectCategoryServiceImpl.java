package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.model.SubjectCategoryJson;
import com.sj.repository.repository.SubjectCategoryRepostiory;
import com.sj.repository.service.SubjectCategoryService;

@Service
@Transactional
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
	@Autowired
	private SubjectCategoryRepostiory repository;

	@Override
	@Cacheable(value = "subjectCategoryCache",key="#id")
	public SubjectCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<SubjectCategoryJson> findAllJson() {
		return Lists.newArrayList(repository.findAll()).stream()
				.map(i -> new SubjectCategoryJson(i))
				.collect(Collectors.toList());
	}

	@Override
	public List<SubjectCategory> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

}
