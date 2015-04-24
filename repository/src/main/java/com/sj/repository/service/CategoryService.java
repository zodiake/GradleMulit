package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.Category;

public interface CategoryService {
	public Category findOne(Long id);

	public Set<Category> findAll();
	
	public Set<Category> findByParent(Category category);
}
