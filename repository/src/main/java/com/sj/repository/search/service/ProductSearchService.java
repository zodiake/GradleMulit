package com.sj.repository.search.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.sj.repository.search.model.ProductSearch;
import com.sj.repository.search.model.ProductSearchOption;

public interface ProductSearchService {
	public Page<ProductSearch> findByOption(ProductSearchOption option,
			Pageable pageable);

	public void save(ProductSearch product);

	public Long count(SearchQuery query);

	public Map<String, String> buildMap(ProductSearchOption option);

	public Page<ProductSearch> findByModel(String model, Pageable pageable);

	public Page<ProductSearch> findByBrand(String brand, Pageable pageable);
}
