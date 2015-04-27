package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.CommonUser;
import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;

public interface PreferProductRepository extends
		PagingAndSortingRepository<PreferProduct, Integer> {
	public Page<PreferProduct> findByUser(CommonUser u, Pageable pageable);

	public Long countByUserAndProduct(CommonUser user, Product p);

	public Long deleteByUserAndProduct(CommonUser user, Product p);
}
