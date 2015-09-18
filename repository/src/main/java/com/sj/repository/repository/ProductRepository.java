package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Brand;
import com.sj.model.model.Product;
import com.sj.model.model.ProductCategory;
import com.sj.model.model.Provider;
import com.sj.model.model.SiteUser;
import com.sj.model.type.ProductStatusEnum;

public interface ProductRepository extends
		PagingAndSortingRepository<Product, Long> {
	Page<Product> findByCreatedByAndStatus(SiteUser user, Pageable pageable,
			ProductStatusEnum status);
	Page<Product> findByCreatedBy(Provider user, Pageable pageable);

	Page<Product> findByBrand(Brand brand, Pageable pageable);

	Page<Product> findByThirdCategoryAndStatus(ProductCategory category,
			Pageable pageable,ProductStatusEnum status);

	Page<Product> findAll(Specification<Product> sp, Pageable pageable);
	
	Product findByIdAndCreatedBy(Long id,Provider user);
	
	Page<Product> findBySecondCategory(ProductCategory category,Pageable pageable);
	
	Product findByIdAndStatus(Long id,ProductStatusEnum status);
	
	Page<Product> findByModelLike(String model,Pageable pageable);
}

