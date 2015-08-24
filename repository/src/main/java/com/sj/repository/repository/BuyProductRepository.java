package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.BuyProduct;

public interface BuyProductRepository extends
		PagingAndSortingRepository<BuyProduct, Long> {

}
