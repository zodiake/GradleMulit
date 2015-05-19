package com.sj.repository.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.repository.repository.InstrumentSearchRepository;
import com.sj.repository.search.model.InstrumentSearch;
import com.sj.repository.search.service.InstrumentSearchService;

@Service
public class InstrumentSearchServiceImpl implements InstrumentSearchService {
	@Autowired
	private InstrumentSearchRepository repository;

	@Override
	public Page<InstrumentSearch> findByBrandAndPriceBetween(String brand,
			double min, double max, Pageable pageable) {
		return repository.findByBrandAndPriceBetween(brand, min, max, pageable);
	}

	@Override
	public void save(InstrumentSearch search) {
		repository.save(search);
	}

}
