package com.sj.repository.converter;

import org.springframework.core.convert.converter.Converter;

import com.sj.model.type.PlaceEnum;

public class StringToPlaceEnumConverter implements
		Converter<String, PlaceEnum> {

	@Override
	public PlaceEnum convert(String source) {
		return PlaceEnum.valueOf(source);
	}
}
