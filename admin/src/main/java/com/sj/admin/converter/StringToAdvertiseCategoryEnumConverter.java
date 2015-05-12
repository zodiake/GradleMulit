package com.sj.admin.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.AdvertiseCategoryEnum;

public class StringToAdvertiseCategoryEnumConverter implements
		Converter<String, AdvertiseCategoryEnum> {

	@Override
	public AdvertiseCategoryEnum convert(String source) {
		if (source == null)
			return null;
		else
			return AdvertiseCategoryEnum.fromString(source);
	}

}
