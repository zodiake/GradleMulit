package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Consumable;
import com.sj.model.type.ProductStatusEnum;
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
	@Override
	public Consumable updateNoPublisher(Consumable consumable) {
		Consumable source = repository.findOne(consumable.getId());
		if(source.getStatus()==ProductStatusEnum.UP)
			source.setStatus(ProductStatusEnum.EXAMINE);
		Consumable result = repository.save(bindNoPublisher(source, consumable));
		return repository.save(result);
	}
	private Consumable bindNoPublisher(Consumable old, Consumable newTarget) {
		old.setCoverImg(newTarget.getCoverImg());
		old.setNameEnglish(newTarget.getNameEnglish());
		old.setModel(newTarget.getModel());
		old.setSpecifications(newTarget.getSpecifications());
		old.setLabel(newTarget.getLabel());
		old.setBrand(newTarget.getBrand());
		old.setSecondCategory(newTarget.getSecondCategory());
		old.setThirdCategory(newTarget.getThirdCategory());
		old.setName(newTarget.getName());
		old.setPrice(newTarget.getPrice());
		old.setContent(newTarget.getContent());
		return old;
	}
}
