package com.sj.admin.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.model.AdvertisementCategory;

public class StringToAdvertiseCategoryConverter implements
		Converter<String, AdvertisementCategory> {

	@Override
	public AdvertisementCategory convert(String source) {
		Long id = Long.valueOf(source);
		return new AdvertisementCategory(id);
	}

}
