package com.sj.repository.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.InformationCategory;
import com.sj.repository.model.InformationCategoryJson;
import com.sj.repository.repository.InformationCategoryRepository;
import com.sj.repository.service.InformationCategoryService;

@Service
@Transactional
public class InformationCategoryServiceImpl implements
		InformationCategoryService {
	@Autowired
	private InformationCategoryRepository repository;

	@Override
	public Page<InformationCategory> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Cacheable(value = "informationCategoryCache", key = "#id")
	public InformationCategory findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public InformationCategory save(InformationCategory category) {
		return repository.save(category);
	}

	@Override
	@CachePut(value = "informationCategoryCache", key = "#category.id")
	public InformationCategory update(InformationCategory category) {
		return repository.save(category);
	}

	@Override
	@CacheEvict(value = "informationCategoryCache",key = "#id")
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Page<InformationCategoryJson> findAllJson() {
		List<InformationCategoryJson> list = findAll().stream()
				.map(c -> new InformationCategoryJson(c))
				.collect(Collectors.toList());
		return new PageImpl<InformationCategoryJson>(list);
	}

	@Override
	public List<InformationCategory> findAll() {
		List<InformationCategory> lists = Lists.newArrayList(repository
				.findAll());
		return lists;
	}

}
