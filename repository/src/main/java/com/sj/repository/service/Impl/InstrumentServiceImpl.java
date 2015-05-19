package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Instrument;
import com.sj.repository.repository.InstrumentRepository;
import com.sj.repository.service.InstrumentService;

@Service
public class InstrumentServiceImpl implements InstrumentService {
	@Autowired
	private InstrumentRepository repository;

	@Override
	public Instrument findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Instrument save(Instrument instrument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument update(Instrument instrument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Instrument> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
