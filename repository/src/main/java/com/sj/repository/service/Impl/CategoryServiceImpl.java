package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.repository.model.Category;
import com.sj.repository.repository.CategoryRepository;
import com.sj.repository.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository repository;

	@Override
	public Category findById(Integer id) {
		return repository.findOne(id);
	}

}
