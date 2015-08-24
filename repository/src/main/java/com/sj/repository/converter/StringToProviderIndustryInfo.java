package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.ProviderIndustryInfo;
import com.sj.repository.repository.ProviderIndustryInfoRepository;

public class StringToProviderIndustryInfo implements
		Converter<String, ProviderIndustryInfo> {
	@Autowired
	private ProviderIndustryInfoRepository repository;

	@Override
	public ProviderIndustryInfo convert(String source) {
		return repository.findOne(Integer.parseInt(source));
	}

}
