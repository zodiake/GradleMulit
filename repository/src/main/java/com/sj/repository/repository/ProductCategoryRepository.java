package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.ProductCategory;
import com.sj.model.type.ActivateEnum;

public interface ProductCategoryRepository extends
		PagingAndSortingRepository<ProductCategory, Long> {

	ProductCategory findByIdAndActivate(Long id, ActivateEnum activate);

	List<ProductCategory> findByParentOrderByNameAsc(ProductCategory category);

	Page<ProductCategory> findByParent(Pageable pageable,
			ProductCategory category);

	List<ProductCategory> findByParentIsNullAndActivate(ActivateEnum activate);

	ProductCategory findByIdAndParent(Long id, ProductCategory category);

	List<ProductCategory> findByParentAndActivate(ProductCategory category,
			ActivateEnum activate);

	ProductCategory findByNameAndActivate(String name, ActivateEnum activate);
	
	ProductCategory findByNameAndActivateAndParentIsNull(String name,ActivateEnum activate);

	ProductCategory findByNameAndParentAndActivate(String name,
			ProductCategory category, ActivateEnum activate);

	ProductCategory findByIdAndActivateAndParentIsNull(Long id,ActivateEnum activate);

	List<ProductCategory> findByParentAndActivate(ProductCategory category,
			ActivateEnum activate, Pageable pageable);
}
