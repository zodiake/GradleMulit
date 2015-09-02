package com.sj.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Provider;
import com.sj.model.type.ActivateEnum;

public interface ProviderRepository extends
		PagingAndSortingRepository<Provider, Long> {
	Provider findByName(String name);

	Provider findById(Long id);

	Page<Provider> findByEnabled(Pageable pageable, ActivateEnum activate);

	Page<Provider> findBySiteAuthority(Pageable pageable, String authority);
}
