package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.model.model.Subject;

public interface ProductService {
	public Page<Product> findByUsers(SiteUser user, Pageable pageable);

	public Product findOne(Long id);
	
	public void addViewCount(Long id);
}
