package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.sj.model.model.Product;
import com.sj.repository.search.model.ProductSearch;

public interface ProductSearchRepository extends
		ElasticsearchCrudRepository<ProductSearch, Long> {
	public Page<Product> findByTitle(String name, Pageable pageable);
}
