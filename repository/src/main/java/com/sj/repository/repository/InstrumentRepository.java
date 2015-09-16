package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Instrument;

public interface InstrumentRepository extends PagingAndSortingRepository<Instrument, Long>{
	public Instrument findById(Long id);
}
