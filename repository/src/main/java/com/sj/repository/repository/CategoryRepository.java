package com.sj.repository.repository;

import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Category;

public interface CategoryRepository extends
		PagingAndSortingRepository<Category, Long> {
	Set<Category> findAll();

	Set<Category> findByParent(Category category);
}
