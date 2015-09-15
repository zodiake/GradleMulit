package com.sj.repository.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sj.model.model.Service;
import com.sj.model.model.Solution;
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
		service.setCreatedTime(Calendar.getInstance());
		service.setStatus(ProductStatusEnum.EXAMINE);
		service.setSolutions(new ArrayList<Solution>());
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
