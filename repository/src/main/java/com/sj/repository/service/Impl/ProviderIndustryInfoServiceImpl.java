package com.sj.repository.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.model.model.ProviderIndustryInfo;
import com.sj.repository.repository.ProviderIndustryInfoRepository;
import com.sj.repository.service.ProviderIndustryInfoService;

@Service
@Transactional
public class ProviderIndustryInfoServiceImpl implements
		ProviderIndustryInfoService {
	@Autowired
	private ProviderIndustryInfoRepository repository;
	
	public List<ProviderIndustryInfo> findAll() {
		
		return repository.findAll();
	}
}
