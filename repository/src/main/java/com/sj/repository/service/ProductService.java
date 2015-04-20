package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;

public interface ProductService {
	public Page<Product> findByUsers(SiteUser user,Pageable pageable);
}
