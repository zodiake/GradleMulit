package com.sj.repository.search.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.repository.search.model.InstrumentSearch;
import com.sj.repository.search.model.InstrumentSearchOption;

public interface InstrumentSearchService {
	public Page<InstrumentSearch> findByBrandAndPriceBetween(String brand,
			double min, double max, Pageable pageable);

	public void save(InstrumentSearch search);
	
	public Page<InstrumentSearch> findByOption(InstrumentSearchOption option,Pageable pageable);
}
