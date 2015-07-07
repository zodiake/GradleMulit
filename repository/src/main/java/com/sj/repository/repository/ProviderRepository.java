package com.sj.repository.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sj.model.model.Provider;

public interface ProviderRepository extends
		PagingAndSortingRepository<Provider, Long> {
	Provider findByName(String name);
}
