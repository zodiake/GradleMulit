package com.sj.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;
import com.sj.repository.model.ProviderJson;

public interface ProviderService {
	public Provider findById(Long id);

	public Provider create(Provider provider);

	public Provider findByName(String name);

	public void authentic(Long id, String auth);

	public Page<Provider> findAllDescAndStatus(Pageable pageable,
			ActivateEnum activate);

	public Provider checkUser(Provider provider, ActivateEnum activate);

	public Provider updateProvider(Provider provider);
	
	public Page<ProviderJson> toJson(Pageable pageable, String authority);

	public Page<Provider> findBySiteAuthority(Pageable pageable,
			String authority);
}
