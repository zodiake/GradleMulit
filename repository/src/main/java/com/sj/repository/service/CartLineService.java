package com.sj.repository.service;

import java.util.List;

import com.sj.model.model.CartLine;

public interface CartLineService {
	void save(Long id, CartLine cartline);

	void remove(Long id, Long cartLineId);

	void updateNumber(Long id, Long cartlineId, int number);

	List<CartLine> findByUser(Long id);

	void clearCartline(Long id, String... lines);
	
	void updateCheck(Long id, Long cartlineId, String check);
	
	List<CartLine> findByUserAndCheck(Long id);
}
