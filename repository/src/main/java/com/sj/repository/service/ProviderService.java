package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;

public interface ProviderService {
	public Provider findOne(Long id);
	
	public Provider create(Provider provider);
	
	public Provider findByName(String name);
	
	public Page<Provider> findAllDescAndStatus(Pageable pageable,ActivateEnum activate);
	
	public Provider checkUser(Provider provider,ActivateEnum activate);
	
	public Provider updateProvider(Provider provider);
	
}
