package com.sj.repository.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.model.model.Category;
import com.sj.repository.repository.CategoryRepository;
import com.sj.repository.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Set<Category> findByParent(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
