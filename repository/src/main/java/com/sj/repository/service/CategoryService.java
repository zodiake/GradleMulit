package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.Category;

public interface CategoryService {
	public Category findById(Integer id);
	public Set<Category> findAll();
}
