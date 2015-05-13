package com.sj.repository.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductCategory;

public interface CategoryService {
	public ProductCategory findOne(Long id);

	public Set<ProductCategory> findAll();

	public Page<ProductCategory> findByParent(Pageable pageable, ProductCategory category);

	public ProductCategory findByIdAndParent(Long id, ProductCategory category);

	public ProductCategory save(ProductCategory category);
	
	public ProductCategory update(ProductCategory category);
}
