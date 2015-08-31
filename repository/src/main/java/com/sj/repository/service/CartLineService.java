package com.sj.repository.service;

import java.util.Set;

import com.sj.model.model.CartLine;

public interface CartLineService {
	void save(Long id, CartLine cartline);

	void remove(Long id, Long cartLineId);

	void updateNumber(Long id, Long cartlineId, int number);

	Set<CartLine> findByUser(Long id);

	void clearCartline(Long id, String... lines);
	
	void updateCheck(Long id, Long cartlineId, String check);
	
	Set<CartLine> findByUserAndCheck(Long id);
}
