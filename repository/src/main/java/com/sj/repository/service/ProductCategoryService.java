package com.sj.repository.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;

public interface ProductCategoryService {
	public ProductCategory findOne(Long id);

	public Set<ProductCategory> findAll();

	public Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category);

	public List<ProductCategory> findByParentAndActivate(
			ProductCategory category, ActivateEnum activate);

	public List<ProductCategory> findAllSecondCategory(ActivateEnum activate);

	public List<ProductCategory> findAllFirstCategory(ActivateEnum activate);

	public ProductCategory findByIdAndParent(Long id, ProductCategory category);

	public ProductCategory save(ProductCategory category);

	public ProductCategory update(ProductCategory category);
}