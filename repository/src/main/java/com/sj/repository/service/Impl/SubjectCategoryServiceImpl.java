package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sj.model.model.SubjectCategory;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.SubjectCategoryJson;
import com.sj.repository.repository.SubjectCategoryRepostiory;
import com.sj.repository.repository.SubjectRepository;
import com.sj.repository.service.SubjectCategoryService;

@Service
@Transactional
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
	@Autowired
	private SubjectCategoryRepostiory repository;
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Cacheable(value = "subjectCategoryCache", key = "#id")
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

	@Override
	@Cacheable(value = "subjectCategoryCache")
	public List<SubjectCategory> findByParent() {
		return repository.findByActivateAndParent(ActivateEnum.ACTIVATE,new SubjectCategory(6l));
	}

}
