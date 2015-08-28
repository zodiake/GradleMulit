package com.sj.repository.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sj.model.model.Service;
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

}
