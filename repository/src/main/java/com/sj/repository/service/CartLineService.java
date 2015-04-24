package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.CartLine;

public interface CartLineService {
	void save(Long id, Long productId, int number);

	void remove(Long id, Long productId);
	
	Set<CartLine> findByUser(Long id);
}
