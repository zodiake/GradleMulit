package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.InformationCategory;

public class StringToInfoCategoryConverter implements
		Converter<String, InformationCategory> {

	@Override
	public InformationCategory convert(String source) {
		return new InformationCategory(Long.parseLong(source));
	}

}
