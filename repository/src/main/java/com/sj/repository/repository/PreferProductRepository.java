package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.PreferProduct;
import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;

public interface PreferProductRepository extends PagingAndSortingRepository<PreferProduct,Integer>{
	public Page<PreferProduct> findByUser(SiteUser u,Pageable pageable);
	
	public Long countByUserAndProduct(SiteUser user,Product p);
}
