package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.sj.repository.search.model.InstrumentSearch;

public interface InstrumentSearchRepository extends
		ElasticsearchCrudRepository<InstrumentSearch, Long> {
	public Page<InstrumentSearch> findByBrandAndPriceBetween(String brand,
			double min, double max, Pageable pageable);

	public Page<InstrumentSearch> findByTitle(String name, Pageable pageable);
}
