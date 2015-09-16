package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.AdvertisementCategory;

public class StringToAdvertiseCategoryConverter implements
		Converter<String, AdvertisementCategory> {

	@Override
	public AdvertisementCategory convert(String source) {
		return new AdvertisementCategory(Long.parseLong(source));
	}
}