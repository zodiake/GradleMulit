package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Instrument;

public interface InstrumentService {
	public Instrument findOne(Long id);
	
	public Instrument save(Instrument instrument);
	
	public Instrument update(Instrument instrument);
	
	public Page<Instrument> findAll(Pageable pageable);
}
