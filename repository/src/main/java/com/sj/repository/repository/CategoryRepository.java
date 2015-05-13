package com.sj.repository.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.ProductCategory;

public interface CategoryRepository extends
		PagingAndSortingRepository<ProductCategory, Long> {
	Set<ProductCategory> findAll();

	Page<ProductCategory> findByParent(Pageable pageable, ProductCategory category);

	ProductCategory findByIdAndParent(Long id, ProductCategory category);
}
