package com.sj.repository.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.Provider;
import com.sj.repository.repository.ProviderRepository;
import com.sj.repository.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService{
	@Autowired
	private ProviderRepository repository;

	@Override
	public Provider findOne(Long id) {
		return repository.findOne(id);
	}

}
