package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Subject;
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
	public List<SubjectCategory> findParentIsNull() {
		return repository.findByActivateAndParentIsNull(ActivateEnum.ACTIVATE);
	}

	@Override
	@Cacheable(value = "indexSubjectsCache")
	public List<SubjectCategory> findByShowOnIndex(
			List<SubjectCategory> subjectCategories) {
		for (int i = 0, len = subjectCategories.size(); i < len; i++) {
			SubjectCategory sc = subjectCategories.get(i);
			List<Subject> subjects = new ArrayList<Subject>();
			List<SubjectCategory> scs = subjectCategories.get(i).getCategories();
			for (int j = 0, jLen = scs.size(); j < jLen; j++) {
				subjects.addAll(subjectRepository.findByShowOnIndexAndActivateAndCategory(
						ActivateEnum.ACTIVATE, ActivateEnum.ACTIVATE,
						scs.get(j)));
			}
			sc.setSubjects(subjects);
		}
		return subjectCategories;
	}
}
