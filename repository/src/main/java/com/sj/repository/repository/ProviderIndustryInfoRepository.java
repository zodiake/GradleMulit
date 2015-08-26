package com.sj.repository.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sj.model.model.ProviderIndustryInfo;

public interface ProviderIndustryInfoRepository extends
		CrudRepository<ProviderIndustryInfo, Integer> {
	List<ProviderIndustryInfo> findAll();
}
