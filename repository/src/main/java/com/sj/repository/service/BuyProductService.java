package com.sj.repository.service;

import java.util.Set;


import com.sj.model.model.BuyProduct;


public interface BuyProductService {
	public Iterable<BuyProduct> save(Set<BuyProduct> products); 
}
