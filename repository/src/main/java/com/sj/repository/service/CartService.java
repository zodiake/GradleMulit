package com.sj.repository.service;

import com.sj.model.model.Product;

public interface CartService {
	public boolean contains(Long id, Product product);
}
