package com.sj.repository.service;

import com.sj.model.model.Instrument;

public interface InstrumentService {
	public Instrument findOne(Long id);
	
	public Instrument save(Instrument instrument);
	
	public Instrument update(Instrument instrument);
}
