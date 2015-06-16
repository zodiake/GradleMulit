package com.sj.repository.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;

public interface ProductSearchService {
	public Page<ProductSearch> findByOption(ProductSearchOption option,
			Pageable pageable);

	public void save(ProductSearch product);
}
