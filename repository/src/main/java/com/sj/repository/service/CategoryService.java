package com.sj.repository.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Category;

public interface CategoryService {
	public Category findOne(Long id);

	public Set<Category> findAll();
	
	public Page<Category> findByParent(Pageable pageable,Category category);
}
