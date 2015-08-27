package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.AdvertismentCategory;

public class StringToAdvertiseCategoryConverter implements
		Converter<String, AdvertismentCategory> {

	@Override
	public AdvertismentCategory convert(String source) {
		return new AdvertismentCategory(Long.parseLong(source));
	}
}
