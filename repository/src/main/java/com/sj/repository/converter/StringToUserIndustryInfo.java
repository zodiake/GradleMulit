package com.sj.repository.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.UserIndustryInfo;
import com.sj.repository.repository.UserIndustryInfoRepository;

public class StringToUserIndustryInfo implements
		Converter<String, UserIndustryInfo> {
	@Autowired
	private UserIndustryInfoRepository repository;

	@Override
	public UserIndustryInfo convert(String source) {
		return repository.findOne(Integer.parseInt(source));
	}

}
