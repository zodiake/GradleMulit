package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.InformationCategory;

public class StringToAdvertiseCategoryConverter implements
		Converter<String, InformationCategory> {

	@Override
	public InformationCategory convert(String source) {
		Long id = Long.valueOf(source);
		return new InformationCategory(id);
	}

}
