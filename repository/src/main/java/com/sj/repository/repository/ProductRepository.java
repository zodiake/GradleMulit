package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	Page<Product> findByUsers(SiteUser user,Pageable pageable);
}