package com.sj.repository.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Category;

public interface CategoryRepository extends
		PagingAndSortingRepository<Category, Long> {
	Set<Category> findAll();

	Page<Category> findByParent(Pageable pageable, Category category);

	Category findByIdAndParent(Long id, Category category);
}
