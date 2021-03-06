package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sj.model.model.Instrument;
import com.sj.model.model.Solution;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.repository.InstrumentRepository;
import com.sj.repository.repository.ProductSearchRepository;
import com.sj.repository.service.InstrumentService;

@Service
@Transactional
public class InstrumentServiceImpl implements InstrumentService {
	@Autowired
	private InstrumentRepository repository;
	
	@Autowired
	private ProductSearchRepository searchRepository;

	@Override
	public Instrument findOne(Long id) {
		Instrument i = repository.findOne(id);
		return i;
	}

	@Override
	public Instrument save(Instrument instrument) {
		Instrument result = repository.save(instrument);
		return result;
	}

	@Override
	public Instrument update(Instrument instrument) {
		Instrument source = repository.findOne(instrument.getId());
		Instrument result = repository.save(bind(source, instrument));
		return result;
	}

	private Instrument bind(Instrument old, Instrument newTarget) {
		old.setBrand(newTarget.getBrand());
		old.setSecondCategory(newTarget.getSecondCategory());
		old.setThirdCategory(newTarget.getThirdCategory());
		old.setName(newTarget.getName());
		old.setCoverImg(newTarget.getCoverImg());
		old.setPrice(newTarget.getPrice());
		old.setContent(newTarget.getContent());
		return old;
	}

	@Override
	public Page<Instrument> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Instrument saveNoPublisher(Instrument instrument) {
		instrument.setCreatedTime(Calendar.getInstance());
		instrument.setStatus(ProductStatusEnum.EXAMINE);
		instrument.setSolutions(new ArrayList<Solution>());
		return repository.save(instrument);
	}

	@Override
	public Instrument updateNoPublisher(Instrument instrument) {
		Instrument source = repository.findById(instrument.getId());
		if (source.getStatus().toString()
				.equals(ProductStatusEnum.UP.toString())){
			source.setStatus(ProductStatusEnum.EXAMINE);
			searchRepository.delete(instrument.getId());
		}
		Instrument result = repository.save(bindNoPublisher(source, instrument));
		return repository.save(result);
	}

	private Instrument bindNoPublisher(Instrument old, Instrument newTarget) {
		old.setCoverImg(newTarget.getCoverImg());
		old.setModel(newTarget.getModel());
		old.setSpecifications(newTarget.getSpecifications());
		old.setLabel(newTarget.getLabel());
		old.setBrand(newTarget.getBrand());
		old.setSecondCategory(newTarget.getSecondCategory());
		old.setThirdCategory(newTarget.getThirdCategory());
		old.setName(newTarget.getName());
		old.setPrice(newTarget.getPrice());
		old.setCoverImg(newTarget.getCoverImg());
		old.setContent(null);
		old.setContent(newTarget.getContent());
		return old;
	}
}
