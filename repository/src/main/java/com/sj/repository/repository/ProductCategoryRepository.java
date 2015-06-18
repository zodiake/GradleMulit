package com.sj.repository.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;

public interface ProductCategoryRepository extends
		PagingAndSortingRepository<ProductCategory, Long> {
	@Override
	Set<ProductCategory> findAll();

	Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category);

	ProductCategory findByIdAndParent(Long id, ProductCategory category);

	List<ProductCategory> findByParentAndActivate(ProductCategory category,
			ActivateEnum activate);
}
