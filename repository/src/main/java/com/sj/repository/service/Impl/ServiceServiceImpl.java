package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sj.model.model.Service;
import com.sj.model.type.ProductStatusEnum;
import com.sj.repository.repository.ServiceRepository;
import com.sj.repository.service.ServiceService;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService{
@Autowired
private ServiceRepository repository;
	@Override
	public Service saveNoPublisher(Service service) {
		return repository.save(service);
	}
	
	@Override
	public Service updateNoPublisher(Service service) {
		Service source = repository.findOne(service.getId());
		if(source.getStatus()==ProductStatusEnum.UP)
			source.setStatus(ProductStatusEnum.EXAMINE);
		Service result = repository.save(bindNoPublisher(source, service));
		return repository.save(result);
	}
	private Service bindNoPublisher(Service old, Service newTarget) {
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
