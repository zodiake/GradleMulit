package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.SubjectCategory;
import com.sj.repository.repository.SubjectCategoryRepostiory;
import com.sj.repository.service.SubjectCategoryService;

@Service
@Transactional
public class SubjectCategoryServiceImpl implements SubjectCategoryService{
	
	@Autowired
	private SubjectCategoryRepostiory repository;

	@Override
	public SubjectCategory findOne(Long id) {
		return repository.findOne(id);
	}

}
