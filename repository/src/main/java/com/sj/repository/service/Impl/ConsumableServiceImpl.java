package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Consumable;
import com.sj.repository.repository.ConsumableRepository;
import com.sj.repository.service.ConsumableService;

@Service
@Transactional
public class ConsumableServiceImpl implements ConsumableService {
	@Autowired
	private ConsumableRepository repository;

	@Override
	public Consumable saveNoPublisher(Consumable consumable) {
		return repository.save(consumable);
	}

}
