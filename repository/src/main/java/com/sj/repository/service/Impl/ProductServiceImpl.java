package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.sj.model.model.Product;
import com.sj.model.model.SiteUser;
import com.sj.model.model.Subject;
import com.sj.repository.repository.ProductRepository;
import com.sj.repository.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repository;

	@Autowired
	private StringRedisTemplate template;

	@Override
	public Page<Product> findByUsers(SiteUser user, Pageable pageable) {
		return repository.findByCreatedBy(user, pageable);
	}

	@Override
	public Product findOne(Long id) {
		return repository.findOne(id);
	}
}
