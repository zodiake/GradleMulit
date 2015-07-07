package com.sj.repository.service;

import com.sj.model.model.Provider;

public interface ProviderService {
	public Provider findOne(Long id);
	
	public Provider create(Provider provider);
	
	public Provider findByName(String name);
}
