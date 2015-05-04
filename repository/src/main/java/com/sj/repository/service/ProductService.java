package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Product;
import com.sj.model.model.Provider;

public interface ProductService {
	public Page<Product> findByUsers(Provider user, Pageable pageable);

	public Product findOne(Long id);
	
	public void addViewCount(Long id);
}
