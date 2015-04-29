package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.CartLine;

public interface CartLineService {
	void save(Long id, CartLine cartline);

	void remove(Long id, Long productId);

	void updateNumber(Long id, Long cartlineId, int number);

	Set<CartLine> findByUser(Long id);
}
